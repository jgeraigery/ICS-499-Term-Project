package com.abc.termproject.utils;

import java.util.ArrayList;
import java.util.List;

import com.abc.termproject.entity.DateInvoiceNumber;
import com.abc.termproject.entity.Delivery;
import com.abc.termproject.entity.Invoice;
import com.abc.termproject.entity.InvoiceItem;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserUtility {
    
    DatabaseUtility db = new DatabaseUtility();
    
    // Builds the list of all invoices for a user
    public String invoiceListBuilder(List<DateInvoiceNumber> userInvoiceList) {
        
        String listText = "";
        
        for (int i = 0; i < userInvoiceList.size(); i++) {
            
            String date = userInvoiceList.get(i).getDate();
            int userID = userInvoiceList.get(i).getUserID();
            int invoiceID = userInvoiceList.get(i).getInvoiceID();
            
            listText += "<li><a href=\"#column2\" onclick=\"   " + invoiceDetailsParameterBuilder(date, userID, invoiceID) + "   \">Date: " +
                date + " | Invoice ID: " + invoiceID + "</a></li>";
        }
        
        return listText;
    }
    
    // Builds the list of all parameters for showInvoiceView() in customer.jsp to display a invoice
    public String invoiceDetailsParameterBuilder(String date, int userID, int invoiceID) {
        
        Invoice myInvoice = db.getInvoice(date, userID, invoiceID);
        
        double myInvoiceTotal = myInvoice.getTotal();
        
        List<InvoiceItem> myInvoiceItemList = myInvoice.getItemList();
        
        int myInvoiceItemListLength = myInvoiceItemList.size();
        
        String functionParameters = "showInvoiceView(" + userID + ", " + invoiceID + ", \'" + date + "\', " + myInvoiceTotal + ", " + myInvoiceItemListLength;
        
        for (int j = 0; j < myInvoiceItemListLength; j++) {
            
            int productID = myInvoiceItemList.get(j).getProductID();
            String name = myInvoiceItemList.get(j).getName();
            String description = myInvoiceItemList.get(j).getDescription();
            double price = myInvoiceItemList.get(j).getPrice();
            int quantity = myInvoiceItemList.get(j).getQuantity();
            
            functionParameters += ", " + productID + ", \'" + name + "\', \'" + description + "\', " + price + ", " + quantity;
        }
        
        functionParameters += ")";
        
        return functionParameters;
        
    }
    
    // Builds the list of all deliveries for a driver
    public String delivListBuilderDriver(List<Delivery> driverDelivList) {
        
        String listText = "";
        
        for (int i = 0; i < driverDelivList.size(); i++) {
            
            Delivery myDelivery = driverDelivList.get(i);
            
            int deliveryID = myDelivery.getDeliveryID();
            int driverID = myDelivery.getDriverID();
            String invoiceDate = myDelivery.getInvoiceDate();
            int customerID = myDelivery.getCustomerID();
            int invoiceID = myDelivery.getInvoiceID();
            String status = myDelivery.getStatus();
            
            listText += "<li><a href=\"#column2\" onclick=\"   " + delivDetailsParameterBuilder(myDelivery) + "   \">Invoice Date: " +
                invoiceDate + " | Invoice ID: " + invoiceID + " | delivery ID: " + deliveryID + " | Customer ID: " + customerID + "</a></li>";
        }
        
        return listText;
    }
    
    // Builds the list of all parameters for showInvoiceView() in driver.jsp to display a deliv
    public String delivDetailsParameterBuilder(Delivery myDelivery) {
        
        int deliveryID = myDelivery.getDeliveryID();
        int driverID = myDelivery.getDriverID();
        String invoiceDate = myDelivery.getInvoiceDate();
        int customerID = myDelivery.getCustomerID();
        int invoiceID = myDelivery.getInvoiceID();
        String status = myDelivery.getStatus();
        
        Invoice myInvoice = db.getInvoice(invoiceDate, customerID, invoiceID);
        
        double myInvoiceTotal = myInvoice.getTotal();
        
        List<InvoiceItem> myInvoiceItemList = myInvoice.getItemList();
        
        int myInvoiceItemListLength = myInvoiceItemList.size();
        
        String functionParameters = "showDelivView(" + deliveryID + ", " + driverID + ", \'" + invoiceDate + "\', " + customerID + ", " + invoiceID + ", \'" + status + "\', " +
            myInvoiceTotal + ", " + myInvoiceItemListLength;
        
        for (int j = 0; j < myInvoiceItemListLength; j++) {
            
            int productID = myInvoiceItemList.get(j).getProductID();
            String name = myInvoiceItemList.get(j).getName();
            String description = myInvoiceItemList.get(j).getDescription();
            double price = myInvoiceItemList.get(j).getPrice();
            int quantity = myInvoiceItemList.get(j).getQuantity();
            
            functionParameters += ", " + productID + ", \'" + name + "\', \'" + description + "\', " + price + ", " + quantity;
        }
        
        functionParameters += ")";
        
        return functionParameters;
        
    }
}
