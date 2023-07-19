<?php
$invoiceId = $_POST['invoiceId'];
$userId = $_POST['userId'];
$date = $_POST['date'];
$prodId = $_POST['prodId'];
$quantity = $_POST['quantity'];

if (!empty($invoiceId) || !empty($userId) || !empty($date) || !empty($prodId) || !empty($quantity)) {
    $host = "localhost";
    $dbUsername = "root";
    $dbPassword = "Quintav85$311";
    $dbName = "EZDB";
    
    // create connection
    $conn = new mysqli($host, $dbUsername, $dbPassword, $dbName);
    
    if (mysqli_connect_error()) {
        die('Connect Error('. mysqli_connect_errno().')'. mysqli_connect_error());
    } else {
        // assumption that we can add items to invoices that already exist, we can adjust later if needed
        // we also assume for now that all fields are valid within the DB such as userId and productId
        $INSERT = "INSERT into invoiceItem (invoiceID, userID, invoiceDate, productID, quantity) values(?, ?, ?, ?, ?)";
        $stmt = $conn->prepare($INSERT);
        $stmt->bind_param("iisii", $invoiceId, $userId, $date, $prodId, $quantity);
        $stmt->execute();
        echo "New record inserted successfully";
    }
    $stmt->close();
    $conn->close();
} else {
    echo "All fields are required";
    die();
}
?>