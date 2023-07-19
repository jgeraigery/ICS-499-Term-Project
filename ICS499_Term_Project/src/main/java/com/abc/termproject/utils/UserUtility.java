package com.abc.termproject.utils;

import java.util.ArrayList;
import java.util.List;

import com.abc.termproject.entity.DateInvoiceNumber;
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
    
    // Returns the total amount of ALL invoices for a user. PROBABLY BELONGS IN DATABASEUTILITY.JAVA
    public double totalInvoiceTotal(List<DateInvoiceNumber> userInvoiceList) {
        
        double totalCount = 0.00;
        
        for (int i = 0; i < userInvoiceList.size(); i++) {
            
            String date = userInvoiceList.get(i).getDate();
            int userID = userInvoiceList.get(i).getUserID();
            int invoiceID = userInvoiceList.get(i).getInvoiceID();
            
            Invoice myInvoice = db.getInvoice(date, userID, invoiceID);
            
            totalCount += myInvoice.getTotal();
        }
        
        return totalCount;
    }
    
    // Builds the list of all invoices for a user
    public String invoiceListBuilder(List<DateInvoiceNumber> userInvoiceList) {
        
        String listText = "";
        
        for (int i = 0; i < userInvoiceList.size(); i++) {
            
            String date = userInvoiceList.get(i).getDate();
            int userID = userInvoiceList.get(i).getUserID();
            int invoiceID = userInvoiceList.get(i).getInvoiceID();
            
            listText += "<li><a href=\"#column2\" onclick=\"   " + invoiceDetailsParameterBuilder(date, userID, invoiceID) + "   \">User ID: " +
                userID + " | Invoice ID: " + invoiceID + " | Date: " + date + "</a></li>";
        }
        
        return listText;
    }
    
    // Builds the list of all parameters for showInvoiceView() in customer.jsp to display a invoice
    public String invoiceDetailsParameterBuilder(String date, int userID, int invoiceID) {
        
        Invoice myInvoice = db.getInvoice(date, userID, invoiceID);
        
        double myInvoiceTotal = myInvoice.getTotal();
        
        List<InvoiceItem> myInvoiceItemList = myInvoice.getItemList();
        
        int myInvoiceItemListLength = myInvoiceItemList.size();
        
        String functionParameters = "showInvoiceView(\'" + date + "\', " + myInvoiceTotal + ", " + myInvoiceItemListLength;
        
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
    
    // Builds the infoboxes for each of the invoices for a user
    /*public String invoiceInfoBuilder(List<DateInvoiceNumber> userInvoiceList) {
        
        String invoiceText = "";
        
        for (int i = 0; i < userInvoiceList.size(); i++) {
            
            String date = userInvoiceList.get(i).getDate();
            int userID = userInvoiceList.get(i).getUserID();
            int invoiceID = userInvoiceList.get(i).getInvoiceID();
            
            Invoice myInvoice = db.getInvoice(date, userID, invoiceID);
            
            List<InvoiceItem> myInvoiceList = myInvoice.getItemList();
            
            invoiceText += "<div class=\"column2\" id=\"invoice" + i + "\" style=\"display:none\"><p>Invoice information for Date " + date +
                "</p><p>Invoice Total: $" + db.getInvoice(date, userID, invoiceID).getTotal() + "</p><ul style=\"list-style-type: none\">" +
                invoiceItemInfoBuilder(myInvoiceList) + "</ul></div>";
        }
        
        return invoiceText;
    }
    
    // Builds the list of all invoice items for an invoice
    public String invoiceItemInfoBuilder(List<InvoiceItem> userInvoiceItemsList) {
        
        String invoiceItemsText = "";
        
        for (int j = 0; j < userInvoiceItemsList.size(); j++) {
            
            int productID = userInvoiceItemsList.get(j).getProductID();
            String name = userInvoiceItemsList.get(j).getName();
            String description = userInvoiceItemsList.get(j).getDescription();
            double price = userInvoiceItemsList.get(j).getPrice();
            int quantity = userInvoiceItemsList.get(j).getQuantity();
            
            invoiceItemsText += "<li>productID: " + productID + " | name: " + name + " | des: " + description + 
                " | price: $" + price + " | quantity: " + quantity + "</li>";
        }
        
        return invoiceItemsText;
    }*/
}
