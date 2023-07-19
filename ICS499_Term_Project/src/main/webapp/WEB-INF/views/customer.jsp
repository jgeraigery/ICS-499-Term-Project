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

<h1>Welcome <%= db.getUserFullName(control.getCurrentUser()) %></h1>
<h1>All Invoice Total: $<%= userUtil.totalInvoiceTotal(db.getInvoiceDates(control.getCurrentUser())) %></h1>

<button class="logout" onclick="window.location.href='http://localhost:8080/login'">Log Out</button>

<div class="row">
    
	<div class="column1">
		<p>Invoice history for <%= db.getUserFullName(control.getCurrentUser()) %></p>
		<ul style="list-style-type: none">
		<%= userUtil.invoiceListBuilder(db.getInvoiceDates(control.getCurrentUser())) %>
        </ul>
	</div>
	
	<div class="column2" id="column2" style="display:none">
    </div>

    <% // We need this script to show and hide the invoice details and to general the invoice view %>
    <script>
    function showInvoiceView() {
        
        x = document.getElementById("column2");
        
        if (x.style.display == "none") {
        	
            x.style.display = "block";
            
            let userID = arguments[0];
            let invoiceID = arguments[1];
            let date = arguments[2];
            
            let myInvoiceTotal = arguments[3];
            
            let myInvoiceItemListLength = arguments[4];
            
            let text = "<p>Invoice information for Date: " + date + " | UserID: " + userID + " | InvoiceID: " + invoiceID +
            "</p><p>Invoice Total: $" + myInvoiceTotal + "</p><ul style=\"list-style-type: none\">";
            
            for (let i = 0; i < myInvoiceItemListLength; i++) {
            	
            	let productID = arguments[5 * i + 5];
                let name = arguments[5 * i + 6];
                let description = arguments[5 * i + 7];
                let price = arguments[5 * i + 8];
                let quantity = arguments[5 * i + 9];
            	
            	text += "<li>productID: " + productID + " | name: " + name + " | des: " + description + 
                " | price: $" + price + " | quantity: " + quantity + "</li>";
            }
            
            text += "</ul>";
            
            document.getElementById("column2").innerHTML = text;
            
        } else {
        	
            x.style.display = "none";
            
        }
    }
    </script>
    
</div>

</body>
</html>