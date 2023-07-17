<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.abc.termproject.controller.*"%>   
<% NavigationController control = new NavigationController(); %> 
<%@page import="com.abc.termproject.utils.*"%>   
<%  DatabaseUtility db = new DatabaseUtility(); %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Customer Page</title>
	<link rel="stylesheet" href="./customerstyle.css">
</head>
<body>
<h1>Welcome <%= db.getUserFullName(control.getCurrentUser()) %></h1>
<button class="logout" onclick="window.location.href='http://localhost:8080/login'">Log Out</button>

<div class="row">
	<div class="column1">
		<p>Invoice history for [Customer Name]</p>
		<ul id="invoiceListBuilder" style="list-style-type: none">   
        </ul>
	</div>
	<div class="column2" id="column2" style="display:none">
	</div>
	<script>
    const list = ["Invoice 1", "Invoice 2", "Invoice 3", "Invoice 4"];

    let text = "";
    for (let i = 0; i < list.length; i++) {
        text += "<li><a href=\"#column2\" onclick=\"showInvoiceView(" + i + ")\">" + list[i] + "</a></li>";
    }

    document.getElementById("invoiceListBuilder").innerHTML = text;
    </script>
    <script>
    function showInvoiceView(i) {
        const list = ["1 Banana", "2 Banana", "3 Banana", "4 Banana"];
        
        var x = document.getElementById("column2");
        if (x.style.display === "none") {
            x.style.display = "block";
            
            let text = "<p>Invoice information for Date " + (i + 1) +
            "</p><ul style=\"list-style-type: none\"><li>" + list[i] +
            "</li></ul>";
            
            document.getElementById("column2").innerHTML = text;
            
        } else {
            x.style.display = "none";
        }
    }
    </script>
</div>

</body>
</html>
