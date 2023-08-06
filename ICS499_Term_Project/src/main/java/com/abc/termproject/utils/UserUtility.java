package com.abc.termproject.utils;

import java.util.List;

import com.abc.termproject.entity.DateInvoiceNumber;
import com.abc.termproject.entity.Delivery;
import com.abc.termproject.entity.Invoice;
import com.abc.termproject.entity.InvoiceItem;
import com.abc.termproject.entity.ContactInfo;

public class UserUtility {
    
    DatabaseUtility db = new DatabaseUtility();
    
    /**
     * Builds the list of all invoices for a user
     * @param (List<DateInvoiceNumber>) - userInvoiceList; the invoice list for a user
     * @return (String) - listText; the HTML code to display the invoice list for a user
     */
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
    
    /**
     * Builds the list of all parameters for showInvoiceView() in customer.jsp to display a invoice
     * @param (String) - date; date to get a invoice
     * @param (int) - userID; userID to get a invoice
     * @param (int) - invoiceID; invoiceID to get a invoice
     * @return (String) = functionParameters; the HTML code that is showInvoiceView(parameters...) to run the showInvoiceView JS function
     */
    public String invoiceDetailsParameterBuilder(String date, int userID, int invoiceID) {
        
        Invoice myInvoice = db.getInvoice(date, userID, invoiceID);
        
        double myInvoiceTotal = myInvoice.getTotal();
        
        List<InvoiceItem> myInvoiceItemList = myInvoice.getItemList();
        
        ContactInfo customerAddress = db.getContactInfoByID(userID);
        
        int sNumber = customerAddress.getsNumber();
        String street = customerAddress.getStreet();
        String city = customerAddress.getCity();
        String state = customerAddress.getState();
        int zip = customerAddress.getZip();
        int prefix = customerAddress.getPrefix();
        int pNumber = customerAddress.getpNumber();
        
        String customerName = db.getUserFullNameByID(userID);
        
        int myInvoiceItemListLength = myInvoiceItemList.size();
        
        String functionParameters = "showInvoiceView(" + userID + ", " + invoiceID + ", \'" + date + "\', " + myInvoiceTotal + ", " +
            sNumber + ", \'" + street + "\', \'" + city + "\', \'" + state + "\', " + zip + ", " + prefix + ", " + pNumber + ", \'" + customerName + "\', " +
            myInvoiceItemListLength;
        
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
    
     
    /**
     * Builds the list of all deliveries for a driver
     * @param (List<Delivery>) - driverDelivList; the delivery list for a driver
     * @return (String) - listText; the HTML code to display the delivery list for a driver
     */
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
            
            listText += "<li class=\"all " + status + " " + invoiceDate + "\"><a href=\"#column2\" onclick=\"   " + delivDetailsParameterBuilder(myDelivery) + "   \">Invoice Date: " +
                invoiceDate + " | Invoice ID: " + invoiceID + " | delivery ID: " + deliveryID + " | Customer ID: " + customerID + " | Status: " + status + "</a></li>";
        }
        
        return listText;
    }
    
    /**
     * Builds the list of all deliveries for a admin
     * @param (List<Delivery>) - driverDelivList; the total delivery list for an admin
     * @return (String) - listText; the HTML code to display the total delivery list for an admin
     */
    public String delivListBuilderAdmin(List<Delivery> driverDelivList) {
        
        String listText = "";
        
        for (int i = 0; i < driverDelivList.size(); i++) {
            
            Delivery myDelivery = driverDelivList.get(i);
            
            int deliveryID = myDelivery.getDeliveryID();
            int driverID = myDelivery.getDriverID();
            String invoiceDate = myDelivery.getInvoiceDate();
            int customerID = myDelivery.getCustomerID();
            int invoiceID = myDelivery.getInvoiceID();
            String status = myDelivery.getStatus();
            
            listText += "<li class=\"all " + status + " " + invoiceDate + " " + driverID + "\"><a href=\"#column2\" onclick=\"   " + delivDetailsParameterBuilderAdmin(myDelivery) + "   \">Invoice Date: " +
                invoiceDate + " | Invoice ID: " + invoiceID + " | delivery ID: " + deliveryID + " | Customer ID: " + customerID + " | Driver ID: " + driverID + " | Status: " + status + "</a></li>";
        }
        
        return listText;
    }
    
    /**
     * Builds the list of all parameters for showInvoiceView() in driver.jsp to display a delivery info
     * @param (Delivery) - myDelivery; myDelivery to get info about it
     * @return (String) = functionParameters; the HTML code that is showDelivView(parameters...) to run the showDelivView JS function
     */
    public String delivDetailsParameterBuilder(Delivery myDelivery) {
        
        int deliveryID = myDelivery.getDeliveryID();
        int driverID = myDelivery.getDriverID();
        String invoiceDate = myDelivery.getInvoiceDate();
        int customerID = myDelivery.getCustomerID();
        int invoiceID = myDelivery.getInvoiceID();
        String status = myDelivery.getStatus();
        
        Invoice myInvoice = db.getInvoice(invoiceDate, customerID, invoiceID);
        
        double myInvoiceTotal = myInvoice.getTotal();
        
        ContactInfo customerAddress = db.getContactInfoByID(customerID);
        
        int sNumber = customerAddress.getsNumber();
        String street = customerAddress.getStreet();
        String city = customerAddress.getCity();
        String state = customerAddress.getState();
        int zip = customerAddress.getZip();
        int prefix = customerAddress.getPrefix();
        int pNumber = customerAddress.getpNumber();
        
        String customerName = db.getUserFullNameByID(customerID);
        
        List<InvoiceItem> myInvoiceItemList = myInvoice.getItemList();
        
        int myInvoiceItemListLength = myInvoiceItemList.size();
        
        String functionParameters = "showDelivView(" + deliveryID + ", " + driverID + ", \'" + invoiceDate + "\', " + customerID + ", " + invoiceID + ", \'" + status + "\', " +
            myInvoiceTotal + ", " + sNumber + ", \'" + street + "\', \'" + city + "\', \'" + state + "\', " + zip + ", " + prefix + ", " + pNumber + ", \'" + customerName + "\', " + myInvoiceItemListLength;
        
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
    
    /**
     * Builds the list of all parameters for showInvoiceView() in admin.jsp to display a delivery info
     * @param (Delivery) - myDelivery; myDelivery to get info about it
     * @return (String) = functionParameters; the HTML code that is showDelivView(parameters...) to run the showDelivView JS function
     */
    public String delivDetailsParameterBuilderAdmin(Delivery myDelivery) {
        
        int deliveryID = myDelivery.getDeliveryID();
        int driverID = myDelivery.getDriverID();
        String invoiceDate = myDelivery.getInvoiceDate();
        int customerID = myDelivery.getCustomerID();
        int invoiceID = myDelivery.getInvoiceID();
        String status = myDelivery.getStatus();
        
        Invoice myInvoice = db.getInvoice(invoiceDate, customerID, invoiceID);
        
        double myInvoiceTotal = myInvoice.getTotal();
        
        ContactInfo customerAddress = db.getContactInfoByID(customerID);
        
        int sNumber = customerAddress.getsNumber();
        String street = customerAddress.getStreet();
        String city = customerAddress.getCity();
        String state = customerAddress.getState();
        int zip = customerAddress.getZip();
        int prefix = customerAddress.getPrefix();
        int pNumber = customerAddress.getpNumber();
        
        String customerName = db.getUserFullNameByID(customerID);
        
        List<InvoiceItem> myInvoiceItemList = myInvoice.getItemList();
        
        int myInvoiceItemListLength = myInvoiceItemList.size();
        
        String functionParameters = "showDelivView(" + deliveryID + ", " + driverID + ", \'" + invoiceDate + "\', " + customerID + ", " + invoiceID + ", \'" + status + "\', " +
            myInvoiceTotal + ", " + sNumber + ", \'" + street + "\', \'" + city + "\', \'" + state + "\', " + zip + ", " + prefix + ", " + pNumber + ", \'" + customerName + "\', " + myInvoiceItemListLength;
        
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
