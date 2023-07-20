<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.abc.termproject.controller.*"%>   
<% NavigationController control = new NavigationController(); %> 
<%@page import="com.abc.termproject.utils.*"%>   
<%  DatabaseUtility db = new DatabaseUtility(); %> 
<%  ReadUtilityCSV readCSVFile = new ReadUtilityCSV(); %> 
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



<!-- Button to upload a file 7/19/23 - Ahmad S --> 
<input type="file" id="csvFile" name="csvFile" onchange="uploadInvoice()"/>

<script>
function uploadInvoice() {
    const file = document.getElementById("csvFile").files[0];
    readCSVFile.readCSV(file);
}
</script>


<h1></h1>



<!-- Doesn't work, can't call java functions from javascript -->
<button class="ReadUtilityCSV" onclick="executeReadCSV()"> Invoice </button>

<script>
function executeReadCSV() {
    if (event.target === document.querySelector('.ReadUtilityCSV')) {
        <% //readCSVFile.readCSV(); %>
    }
}
</script>



<h1></h1>



<!-- This form is used to submit invoiceItem data to the database, the current problem is connecting the form to the correct method, tries to use Servlet -->
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


<h1></h1>


<!-- Tries to use Servlet -->
<form action="upload" method="post" enctype="multipart/form-data">
	<label for="file" class="btn">Input Invoice</label>
	<input id="file" name="file" type="file">
	<button type="submit">Input Invoice</button>
</form>





<h1></h1>



<!-- test -->
<input type="file" id="readCSV" name="csvFile" onchange="upload()"/>

<button onclick="upload()">Upload Invoice</button>

<button id="readCSV" type="button">Upload Invoice</button>

<script>
//function upload() {
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
//}
</script>



<h1></h1>




<!-- Alexey test, it doesn't work -->
<form action="insert.php" method="post">
    <input type="submit" value="Execute Java Function">
</form>



<h1></h1>





<!-- Currently this button only opens the file directory, script will be moved in a future push, this was for basic testing - Ahmad S -->
<button id="readCSVtest" type="button">Upload Invoice</button>
                       
<script>
    const readCSV = document.getElementById("readCSVtest");
    readCSV.addEventListener("click", function(e) {
        window.showDirectoryPicker({
            startIn: 'desktop'
        });
    });
</script>






<button class="logout" onclick="window.location.href='http://localhost:8080/login'">Log Out</button>

<div class="row">
	<div class="column1">
		<p>Deliveries for <%= db.getUserFullName(control.getCurrentUser()) %></p>
		
        <ul style="list-style-type: none">
        <%= userUtil.delivListBuilderDriver(db.getDeliveriesAll()) %>
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