<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.abc.termproject.controller.*"%>   
<% NavigationController control = new NavigationController(); %> 
<%@page import="com.abc.termproject.utils.*"%>   
<%  DatabaseUtility db = new DatabaseUtility(); %> 
<%  ReadUtilityCSV readCSVFile = new ReadUtilityCSV(); %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Admin Page</title>
	<link rel="stylesheet" href="./adminstyle.css">
</head>
<body>
<h1>Welcome <%= db.getUserFullName(control.getCurrentUser()) %></h1>
<!--  
<button class="ReadUtilityCSV" onclick="executeReadCSV()"> Invoice </button>
<script>
function executeReadCSV() {
    if (event.target === document.querySelector('.ReadUtilityCSV')) {
        <% //readCSVFile.readCSV(); %>
    }
}
</script>
-->
<!-- This form is used to submit invoiceItem data to the database, the current problem is connecting the form to the correct method -->
<form action="/upload" method="post">
	<table>
		<tr>
			<td>Invoice ID:</td>
			<td><input type="text" name="invoiceId" required></td>
		</tr>
		<tr>
			<td>User ID:</td>
			<td><input type="text" name="userId" required></td>
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
			<td><input type="submit" value="Upload Invoice"></td>
			<td><input type="reset" value="Reset"></td>
		</tr>
	</table>
</form>


<!--
<form action="upload" method="post" enctype="multipart/form-data">
	<label for="file" class="btn">Input Invoice</label>
	<input id="file" name="file" type="file">
	<button type="submit">Input Invoice</button>
</form>
-->
<!--  <button onclick="upload()">Upload Invoice</button>-->

<!--  
<script>
	const readCSV = document.getElementById("readCSV");
	readCSV.addEventListener("click", async () => {
		try {
			let directory = await window.showDirectoryPicker({
				startIn: 'desktop'
			});
			
			for await (const entry of directory.values()) {
				let newEl = document.createElement('div');
				newEl.innerHTML = '<strong>${entry.name}</strong> - ${entry.kind}';
				document.getElementById('folder-info').append(newEl);
			}
		} catch(x) {
			console.log(x);
		}
	});
</script>
-->

<button class="logout" onclick="window.location.href='http://localhost:8080/login'">Log Out</button>
<div class="row">
	<div class="column1">
		<p>Deliveries for [Today's Date]</p>
		<ul id="delivListBuilder" style="list-style-type: none">   
		</ul>
	</div>
	<div class="column2" id="column2" style="display:none">
	</div>
	<script>
    const list = ["Delivery 1", "Delivery 2", "Delivery 3", "Delivery 4"];

    let text = "";
    for (let i = 0; i < list.length; i++) {
    	text += "<li><a href=\"#column2\" onclick=\"showDelivView(" + i + ")\">" + list[i] + "</a></li>";
    }

    document.getElementById("delivListBuilder").innerHTML = text;
    </script>
    <script>
    
    
    function showDelivView(i) {
        const list = ["1 Banana", "2 Banana", "3 Banana", "4 Banana"];
        
        var x = document.getElementById("column2");
        if (x.style.display === "none") {
            x.style.display = "block";
            
            let text = "<p>Invoice information for Delivery " + (i + 1) +
            "</p><ul style=\"list-style-type: none\"><li>" + list[i] +
            "</li></ul><button class=\"cancel\" onclick=\"\">Cancel Delivery</button><button class=\"verify\" onclick=\"\">Verify Delivery</button>";
            
            document.getElementById("column2").innerHTML = text;
            
        } else {
            x.style.display = "none";
        }
    }
    </script>
</div>
</body>
</html>