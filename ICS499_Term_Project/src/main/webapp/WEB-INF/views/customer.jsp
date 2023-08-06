<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.abc.termproject.controller.*"%>   
<% NavigationController control = new NavigationController(); %> 
<%@page import="com.abc.termproject.utils.*"%>
<%@page import="com.abc.termproject.entity.DateInvoiceNumber"%>
<%  DatabaseUtility db = new DatabaseUtility(); %> 
<%  UserUtility userUtil = new UserUtility(); %> 
<!DOCTYPE html>

<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Customer Page</title>
	<link rel="stylesheet" href="./customerstyle.css">
</head>

<body>

<h1>EZ Invoicing</h1>

<button class="logout" onclick="window.location.href='http://localhost:8080/login'">Log Out</button>

<br></br>
<br></br>

<div class="row">
    
	<div class="column1">
		<p>Invoice history for <%= db.getUserFullName(control.getCurrentUser()) %>:</p>
		<ul style="list-style-type: none">
		<%= userUtil.invoiceListBuilder(db.getInvoiceDates(control.getCurrentUser())) %>
        </ul>
	</div>
	
	<div class="column2" id="column2" style="display:none">
    </div>

    <!-- We need this script to show and hide the invoice details and to general the invoice view -->
    <script>
    function showInvoiceView() {
        
        x = document.getElementById("column2");
        
        if (x.style.display == "none") {
        	
            x.style.display = "block";
            
            let customerID = arguments[0];
            let invoiceID = arguments[1];
            let invoiceDate = arguments[2];
            
            let myInvoiceTotal = (arguments[3]).toFixed(2);
            
            let sNumber = arguments[4];
            let street = arguments[5];
            let city = arguments[6];
            let state = arguments[7];
            let zip = arguments[8];
            let prefix = arguments[9];
            let pNumber = arguments[10];
            
            let customerName = arguments[11];
            
            let myInvoiceItemListLength = arguments[12];
            
            let text = "<div class=\"row\">";
            
            text += "<div id=\"invoiceDetails\">";
            
            text += "<ul style=\"list-style-type: none\">";
            
            text += "<li>Date: " + invoiceDate + "</li>";
            text += "<li>Invoice ID: " + invoiceID + "</li>";
            text += "<li>Customer ID: " + customerID + "</li>";
            
            text += "</ul>";
            
            text += "</div><div id=\"customerDetails\">";
            
            text += "<ul style=\"list-style-type: none\">";
            
            text += "<li>" + customerName + "</li>";
            text += "<li>" + sNumber + " " + street + "</li>";
            text += "<li>" + city + ", " + state + " " + zip + "</li>";
            text += "<li>(" + prefix + ") " + String(pNumber).substr(0, 3) + "-" + String(pNumber).substr(3) + "</li>";
            
            text += "</ul>";
            
            text += "</div></div>";
            
            //let text = "<p>Invoice information for Date: " + date + " | InvoiceID: " + invoiceID + "</p>";
            
            text += "<ul style=\"list-style-type: none\">";
            
            for (let i = 0; i < myInvoiceItemListLength; i++) {
            	
            	let productID = arguments[5 * i + 13];
                let name = arguments[5 * i + 14];
                let description = arguments[5 * i + 15];
                let price = (arguments[5 * i + 16]).toFixed(2);
                let quantity = arguments[5 * i + 17];
            	
                text += "<li>productID: " + productID + " | name: " + name + " | des: " + description + 
                " | price: $" + price + " | quantity: " + quantity + "</li>";
            }
            
            text += "<br></br>";
            
            text += "<p style=\"position: absolute; right: 310px;\">Invoice Total: $" + myInvoiceTotal + "</p>";
            
            text += "<br></br>";
            
            text += "</ul>";
            
            x.innerHTML = text;
            
        } else {
        	
            x.style.display = "none";
            
        }
    }
    </script>
    
    
</div>

</body>
</html>