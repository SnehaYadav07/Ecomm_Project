<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Home</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
    .dashboard-box {
        margin: 20px 0;
        padding: 20px;
        border-radius: 8px;
        background-color: #f9f9f9;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        text-align: center;
    }
    .dashboard-box:hover {
        background-color: #f0f0f0;
    }
    footer {
        background-color: #f8f9fa;
        text-align: center;
        padding: 10px;
        position: fixed;
        bottom: 0;
        width: 100%;
    }
</style>
</head>
<body>
<%
    String admin = (String) session.getAttribute("admin");
    if(admin == null) {
        // If the adminName attribute is not found, redirect to the login page
        response.sendRedirect("login.jsp?message=Access Denied. Login as admin!");
        return; // Prevents further rendering of the page
    }
%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Admin Dashboard</a>
</nav>

<div class="container">
    <div class="row">
        <div class="col-md-4 dashboard-box">
            <h4>Add Product</h4>
            <p>Use this option to add new products to your store.</p>
            <a href="addProduct.jsp" class="btn btn-primary">Add Product</a>
        </div>
        <div class="col-md-4 dashboard-box">
            <h4>Remove Product</h4>
            <p>Select products to remove them from your store.</p>
            <a href="removeProduct.jsp" class="btn btn-danger">Remove Product</a>
        </div>
        <div class="col-md-4 dashboard-box">
            <h4>Modify Product</h4>
            <p>Update product details, prices, and more.</p>
            <a href="modifyProduct.jsp" class="btn btn-success">Modify Product</a>
        </div>
        <div class="col-md-4 dashboard-box">
            <h4>Add Category</h4>
            <p>Add more categories to the Ecommerce site.</p>
            <a href="addCategory.jsp" class="btn btn-success">Add Category</a>
        </div>
    </div>
</div>

<footer>
    © 2024 Your Company. All rights reserved.
</footer>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    // Get the message from the URL query string
    var urlParams = new URLSearchParams(window.location.search);
    var message = urlParams.get('message');

    if (message) {
        // Display the message as a toast
        alert(message);

        // Refresh the page after displaying the message
        window.location.href = "adminHome.jsp";
    }
</script>

</body>
</html>
