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

<h1>Welcome <%= db.getUserFullName(control.getCurrentUser()) %></h1>
<button class="logout" onclick="window.location.href='http://localhost:8080/login'">Log Out</button>

<div class="row">
    
	<div class="column1">
	
		<p>Deliveries for <%= db.getUserFullName(control.getCurrentUser()) %></p>
		
		<ul style="list-style-type: none">
		<%= userUtil.delivListBuilderDriver(db.getDeliveriesByDriver(db.getUserIDByUserName(control.getCurrentUser()))) %>
		</ul>
	</div>
	
	<div class="column2" id="column2" style="display:none">
	</div>
	
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
            
            //let myInvoiceTotal = arguments[6];
            
            //let myInvoiceItemListLength = arguments[7];
            
            let text = "<p>Delivery information for deliveryID: " + deliveryID + " | driverID: " + driverID + " | invoiceDate: " + invoiceDate +
            " | customerID: " + customerID + " | invoiceID: " + invoiceID + " | status: " + status + "</p>";
            
            //text += "<p>Invoice Total: $" + myInvoiceTotal + "</p>";
            
            text += "<ul style=\"list-style-type: none\">";
            
            //for (let i = 0; i < myInvoiceItemListLength; i++) {
                
                //let productID = arguments[5 * i + 8];
                //let name = arguments[5 * i + 9];
                //let description = arguments[5 * i + 10];
                //let price = arguments[5 * i + 11];
                //let quantity = arguments[5 * i + 12];
                
                //text += "<li>productID: " + productID + " | name: " + name + " | des: " + description + 
                //" | price: $" + price + " | quantity: " + quantity + "</li>";
            //}
            
            text += "</ul>";
            
            text += "<button class=\"cancel\" onclick=\"\">Cancel Delivery</button>";
            
            text += "<button class=\"verify\" onclick=\"\">Verify Delivery</button>";
            
            document.getElementById("column2").innerHTML = text;
            
        } else {
            
            x.style.display = "none";
            
        }
    }
    </script>
    
</div>

</body>
</html>