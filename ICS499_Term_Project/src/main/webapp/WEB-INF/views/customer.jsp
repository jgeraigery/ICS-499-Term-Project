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
	
	<%= userUtil.invoiceInfoBuilder(db.getInvoiceDates(control.getCurrentUser())) %>

    <% // We still need this script to show and hide the invoice details %>
    <script>
    function showInvoiceView(i) {
        
        x = document.getElementById("invoice" + i);
        
        if (x.style.display == "none") {
        	
            x.style.display = "block";
            
        } else {
        	
            x.style.display = "none";
            
        }
    }
    </script>
    
</div>

</body>
</html>