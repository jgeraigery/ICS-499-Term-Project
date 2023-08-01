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
	<title>Admin Page</title>
	<link rel="stylesheet" href="./adminstyle.css">
</head>
<body>
<h1>Welcome <%= db.getUserFullName(control.getCurrentUser()) %></h1>

<!-- This form is used to submit invoiceItem data to the database
	 Currently adds one item at a time to any given invoice
	 Also assumes user knows what they're doing and doesn't input any bad values
	 EX) different user id or date than original invoice info 
<form action="/admin" method="post">
	<table>
	 	<tr>
			<td>Invoice ID:</td>
			<td><input type="text" name="invoiceId" required></td>
		</tr>
		<tr>
			<td>Customer ID:</td>
			<td><input type="text" name="customerId" required></td>
		</tr>
		<tr>
			<td>Invoice Date:</td>
			<td><input type="date" name="date" required></td>
		</tr>
		<tr>
			<td>Product Id:</td>
			<td><input type="text" name="prodId" required></td>
		</tr>
		<tr>
			<td>Quantity:</td>
			<td><input type="text" name="quantity" required></td>
		</tr>
		<tr>
			<td><input type="submit" value="Upload Invoice Item"></td>
			<td><input type="reset" value="Reset"></td>
		</tr>
	</table>
</form>
-->

<form action="/admin" enctype="multipart/form-data" method="post">
	<input type="file" name="upload">
	<input type="submit">
</form>

<button class="logout" onclick="window.location.href='http://localhost:8080/login'">Log Out</button>

<div class="row">
	<div class="column1">
	    
	    <p>View Filters: </p>
	    
	    <button onclick="showAll()">All</button>
	    <button onclick="showStatusOnly('progress')">In Progress</button>
	    <button onclick="showStatusOnly('delivered')">Delivered</button>
	    <button onclick="showStatusOnly('canceled')">Canceled</button>
	    
		<p>All Deliveries to View for <%= db.getUserFullName(control.getCurrentUser()) %></p>
		
        <ul style="list-style-type: none">
        <%= userUtil.delivListBuilderAdmin(db.getDeliveriesAll()) %>
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
            
            let myInvoiceItemListLength = arguments[7];
            
            let text = "<p>Invoice information for Invoice Date: " + invoiceDate + " | Invoice ID: " + invoiceID + 
            " | Delivery ID: " + deliveryID + " | Customer ID: " + customerID + " | Current Status: " + status + "</p>";
            
            text += "<p>Invoice Total: $" + myInvoiceTotal + "</p>";
            
            text += "<ul style=\"list-style-type: none\">";
            
            for (let i = 0; i < myInvoiceItemListLength; i++) {
                
                let productID = arguments[5 * i + 8];
                let name = arguments[5 * i + 9];
                let description = arguments[5 * i + 10];
                let price = (arguments[5 * i + 11]).toFixed(2);
                let quantity = arguments[5 * i + 12];
                
                text += "<li>productID: " + productID + " | name: " + name + " | des: " + description + 
                " | price: $" + price + " | quantity: " + quantity + "</li>";
            }
            
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