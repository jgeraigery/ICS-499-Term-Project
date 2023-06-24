<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="java.util.*"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Page</title>
</head>
<body>
<h1>Welcome Admin!</h1>
<%!
    // This was a function, but I commented it out and put everything together to make it easier to edit my code.
    
    /*
    public static ArrayList<ArrayList<String>> deliveryListBuilder() {
        
        ArrayList<ArrayList<String>> deliveryList =  new ArrayList<ArrayList<String>>();
        
        ArrayList<String> a1 = new ArrayList<String>();
        
        a1.add("1");
        a1.add("Apple");
        
        deliveryList.add(a1);
        
        ArrayList<String> a2 = new ArrayList<String>();
        
        a2.add("2");
        a2.add("Banana");
        
        deliveryList.add(a2);
        
        ArrayList<String> a3 = new ArrayList<String>();
        
        a3.add("3");
        a3.add("Cherry");
        
        deliveryList.add(a3);
        
        ArrayList<String> a4 = new ArrayList<String>();
        
        a4.add("4");
        a4.add("Date");
        
        deliveryList.add(a4);
        
        ArrayList<String> a5 = new ArrayList<String>();
        
        a5.add("5");
        a5.add("Eggplant");
        
        deliveryList.add(a5);
        
        return deliveryList;
    }
    */
    
%>
<%
    //deliveryListBuilder();

    // This part builds the ArrayList that holds other ArrayLists that will in the future store data that you will get from the database.
    
    ArrayList<ArrayList<String>> deliveryList =  new ArrayList<ArrayList<String>>();

    ArrayList<String> a1 = new ArrayList<String>();

    a1.add("1");
    a1.add("Apple");

    deliveryList.add(a1);

    ArrayList<String> a2 = new ArrayList<String>();

    a2.add("2");
    a2.add("Banana");

    deliveryList.add(a2);

    ArrayList<String> a3 = new ArrayList<String>();

    a3.add("3");
    a3.add("Cherry");

    deliveryList.add(a3);

    ArrayList<String> a4 = new ArrayList<String>();

    a4.add("4");
    a4.add("Date");

    deliveryList.add(a4);

    ArrayList<String> a5 = new ArrayList<String>();

    a5.add("5");
    a5.add("Eggplant");

    deliveryList.add(a5);
    
    // This part displays contents of the deliveryList ArrayList.
    
    for (int i = 0; i < deliveryList.size(); i++) {
        for (int j = 0; j < deliveryList.get(i).size(); j++) {
            out.println(deliveryList.get(i).get(j) + "   ");
        }
        out.println("<br/>");
    }
    
    // This is a framework on how a admin would select a single delivery, the problem is that it asks for input before you can access the Admin page.
    
    /*
    Scanner myObj = new Scanner(System.in);
    
    System.out.print("Enter delivery number: ");
    int delivNum = myObj.nextInt();
    
    System.out.print("Delivery info is");
    
    for (int k = 1; k < deliveryList.get(delivNum - 1).size(); k++) {
        System.out.print(" " + deliveryList.get(delivNum - 1).get(k));
    }
    
    System.out.println();
    */
%>
<br />
<br />
<button onclick="window.location.href='http://localhost:8080/login'">Log Out</button>
</body>
</html>