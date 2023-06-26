<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="java.util.*"%>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Customer Page</title>
	<link rel="stylesheet" href="./customerstyle.css">
</head>
<body>
<h1>Welcome [Customer Name Goes Here]</h1>
<button class="logout" onclick="window.location.href='http://localhost:8080/login'">Log Out</button>
<%!
    // This was a function, but I commented it out and put everything together to make it easier to edit my code.
    
    /*
    public static ArrayList<ArrayList<String>> invoiceListBuilder() {
        
        ArrayList<ArrayList<String>> invoiceList =  new ArrayList<ArrayList<String>>();
        
        ArrayList<String> a1 = new ArrayList<String>();
        
        a1.add("1");
        a1.add("Apple");
        
        invoiceList.add(a1);
        
        ArrayList<String> a2 = new ArrayList<String>();
        
        a2.add("2");
        a2.add("Banana");
        
        invoiceList.add(a2);
        
        ArrayList<String> a3 = new ArrayList<String>();
        
        a3.add("3");
        a3.add("Cherry");
        
        invoiceList.add(a3);
        
        ArrayList<String> a4 = new ArrayList<String>();
        
        a4.add("4");
        a4.add("Date");
        
        invoiceList.add(a4);
        
        ArrayList<String> a5 = new ArrayList<String>();
        
        a5.add("5");
        a5.add("Eggplant");
        
        invoiceList.add(a5);
        
        return invoiceList;
    }
    */
    
%>
<%
    //invoiceListBuilder();

    // This part builds the ArrayList that holds other ArrayLists that will in the future store data that you will get from the database.
    /*
    ArrayList<ArrayList<String>> invoiceList =  new ArrayList<ArrayList<String>>();

    ArrayList<String> a1 = new ArrayList<String>();

    a1.add("1");
    a1.add("Apple");

    invoiceList.add(a1);

    ArrayList<String> a2 = new ArrayList<String>();

    a2.add("2");
    a2.add("Banana");

    invoiceList.add(a2);

    ArrayList<String> a3 = new ArrayList<String>();

    a3.add("3");
    a3.add("Cherry");

    invoiceList.add(a3);

    ArrayList<String> a4 = new ArrayList<String>();

    a4.add("4");
    a4.add("Date");

    invoiceList.add(a4);

    ArrayList<String> a5 = new ArrayList<String>();

    a5.add("5");
    a5.add("Eggplant");

    invoiceList.add(a5);
    */
    // This part displays contents of the invoiceList ArrayList.
    /*
    for (int i = 0; i < invoiceList.size(); i++) {
        for (int j = 0; j < invoiceList.get(i).size(); j++) {
            out.println(invoiceList.get(i).get(j) + "   ");
        }
        out.println("<br/>");
    } */
    
    // This is a framework on how a customer would select a single delivery, the problem is that it asks for input before you can access the Customer page.
    
    /*
    Scanner myObj = new Scanner(System.in);
    
    System.out.print("Enter invoice number: ");
    int invoiceNum = myObj.nextInt();
    
    System.out.print("Invoice info is");
    
    for (int k = 1; k < invoiceList.get(invoiceNum - 1).size(); k++) {
        System.out.print(" " + invoiceList.get(invoiceNum - 1).get(k));
    }
    
    System.out.println();
    */
%>
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