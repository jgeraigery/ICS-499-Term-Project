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

<h1>EZ Invoicing</h1>

<button class="logout" onclick="window.location.href='http://localhost:8080/login'">Log Out</button>

<form action="/admin" enctype="multipart/form-data" method="post">
	<input type="file" name="upload" id="fileUpload">
	<input type="submit" value="Upload" id="fileSubmit">
</form>

<div class="row">
	<div class="column1">
	    
	    <button onclick="showAll()">All</button>
	    <button onclick="showStatusOnly('progress')">In Progress</button>
	    <button onclick="showStatusOnly('delivered')">Delivered</button>
	    <button onclick="showStatusOnly('canceled')">Canceled</button>
	    
	    <p></p>
	    
	    <div style="display:inline-block;">
	    
	        <form id="driverForm">
                Enter driver ID in format (#): <input type="text" name="driverEntry" class="enterFormBox">
            </form>
            <button id="driverFilterButton" onclick="filterTextbox('driverForm')">Driver Filter</button>
            
            <p></p>
            
		    <form id="dateForm">
	            Enter date in format (YYYY-MM-DD): <input type="text" name="dateEntry" class="enterFormBox">
	        </form>
	        <button onclick="filterTextbox('dateForm')">Date Filter</button>
	        
	    </div>
	    
		<p>&nbsp;All Deliveries View for <%= db.getUserFullName(control.getCurrentUser()) %>:</p>
		
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
            
            let customerName = arguments[14];
            
            let myInvoiceItemListLength = arguments[15];
            
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
            
            //let text = "<p>Invoice information for Invoice Date: " + invoiceDate + " | Invoice ID: " + invoiceID + 
            //" | Delivery ID: " + deliveryID + " | Customer ID: " + customerID + " | Current Status: " + status + "</p>";
            
            //text += "<p>Address: " + sNumber + " " + street + ", " + city + ", " + state + " " + zip + "</p>";
            
            //text += "<p>Phone Number: " + prefix + pNumber + "</p>";
            
            text += "<ul style=\"list-style-type: none\">";
            
            for (let i = 0; i < myInvoiceItemListLength; i++) {
                
            	let productID = arguments[5 * i + 16];
                let name = arguments[5 * i + 17];
                let description = arguments[5 * i + 18];
                let price = (arguments[5 * i + 19]).toFixed(2);
                let quantity = arguments[5 * i + 20];
                
                text += "<li>productID: " + productID + " | name: " + name + " | des: " + description + 
                " | price: $" + price + " | quantity: " + quantity + "</li>";
            }
            
            text += "<br></br>";
            
            text += "<p style=\"position: absolute; right: 15%;\">Invoice Total: $" + myInvoiceTotal + "</p>";
            
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