<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.abc.termproject.controller.*"%>   
<% NavigationController control = new NavigationController(); %> 
<%@page import="com.abc.termproject.utils.*"%>   
<%  DatabaseUtility db = new DatabaseUtility(); %>
<%  UserUtility userUtil = new UserUtility(); %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Driver Page</title>
	<link rel="stylesheet" href="./driverstyle.css">
</head>
<body>

<h1>EZ Invoicing</h1>
<h2>Welcome <%= db.getUserFullName(control.getCurrentUser()) %></h2>
<button class="logout" onclick="window.location.href='http://localhost:8080/login'">Log Out</button>

<div class="row">
    
	<div class="column1">
	
	    <p>View Filters: </p>
        
        <button onclick="showAll()">All</button>
        <button onclick="showStatusOnly('progress')">In Progress</button>
        <button onclick="showStatusOnly('delivered')">Delivered</button>
        <button onclick="showStatusOnly('canceled')">Canceled</button>
        
        <p></p>
        <form id="dateForm">
            Enter date in format (YYYY-MM-DD): <input type="text" name="dateEntry">
        </form>
        <button onclick="filterTextbox('dateForm')">Date Filter</button>
	
		<p>Deliveries for <%= db.getUserFullName(control.getCurrentUser()) %></p>
		
		<ul style="list-style-type: none">
		<%= userUtil.delivListBuilderDriver(db.getDeliveriesByDriver(db.getUserIDByUserName(control.getCurrentUser()))) %>
		</ul>
	</div>
	
	<div class="column2" id="column2" style="display:none">
	</div>
	
	<script>
    function showAll() {
        
        x = document.getElementsByClassName("all");
        
        for (let i = 0; i < x.length; i++) {
            x[i].style.display = "block";
        }
    }
    
    function showStatusOnly() {
        
        let status = arguments[0];
        
        x = document.getElementsByClassName("all");
        
        for (let i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        
        x = document.getElementsByClassName(status);
        
        for (let i = 0; i < x.length; i++) {
            x[i].style.display = "block";
        }
        
        document.getElementById("column2").style.display = "none";
    }
    
    function filterTextbox() {
        
        let textboxID = arguments[0];
        
        x = document.getElementsByClassName("all");
        
        for (let i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        
        y = document.getElementById(textboxID);
        
        dateFilter = y.elements[0].value;
        
        x = document.getElementsByClassName(dateFilter);
        
        for (let i = 0; i < x.length; i++) {
            x[i].style.display = "block";
        }
        
        document.getElementById("column2").style.display = "none";
    }
    </script>
	
    <script>
    function showDelivView() {
        
        x = document.getElementById("column2");
        
        if (x.style.display == "none") {
            
            x.style.display = "block";
            
            let deliveryID = arguments[0];
            let driverID = arguments[1];
            let invoiceDate = arguments[2];
            let customerID = arguments[3];
            let invoiceID = arguments[4];
            let status = arguments[5];
            
            let myInvoiceTotal = (arguments[6]).toFixed(2);
            
            let sNumber = arguments[7];
            let street = arguments[8];
            let city = arguments[9];
            let state = arguments[10];
            let zip = arguments[11];
            let prefix = arguments[12];
            let pNumber = arguments[13];
            
            let myInvoiceItemListLength = arguments[14];
            
            let text = "<p>Invoice information for Invoice Date: " + invoiceDate + " | Invoice ID: " + invoiceID + 
            " | Delivery ID: " + deliveryID + " | Customer ID: " + customerID + " | Current Status: " + status + "</p>";
            
            text += "<p>Address: " + sNumber + " " + street + ", " + city + ", " + state + " " + zip + "</p>";
            
            text += "<p>Phone Number: " + prefix + pNumber + "</p>";
            
            text += "<p>Invoice Total: $" + myInvoiceTotal + "</p>";
            
            text += "<ul style=\"list-style-type: none\">";
            
            for (let i = 0; i < myInvoiceItemListLength; i++) {
                
                let productID = arguments[5 * i + 15];
                let name = arguments[5 * i + 16];
                let description = arguments[5 * i + 17];
                let price = (arguments[5 * i + 18]).toFixed(2);
                let quantity = arguments[5 * i + 19];
                
                text += "<li>productID: " + productID + " | name: " + name + " | des: " + description + 
                " | price: $" + price + " | quantity: " + quantity + "</li>";
            }
            
            text += "</ul>";
            
            text += "<form action=\"/driver\" method=\"post\"><input type=\"submit\" name=\"command\" value=\"Verify " + deliveryID + "\"/></form>";
            
            text += "<p></p>";
            
            text += "<form action=\"/driver\" method=\"post\"><input type=\"submit\" name=\"command\" value=\"Cancel " + deliveryID + "\"/></form>";
            
            text += "<p></p>";
            
            text += "<form action=\"/driver\" method=\"post\"><input type=\"submit\" name=\"command\" value=\"InProg " + deliveryID + "\"/></form>";
            
            x.innerHTML = text;
            
        } else {
            
            x.style.display = "none";
            
        }
    }
    </script>
    
</div>

</body>
</html>