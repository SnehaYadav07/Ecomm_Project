<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ecomm.model.Category" %>
<%@ page import="com.ecomm.dao.CategoryDao" %>
<%@ page import="com.ecomm.util.DBConnection" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product</title>
    
    <link rel="stylesheet" href="css/linearicons.css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/themify-icons.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/nice-select.css">
    <link rel="stylesheet" href="css/nouislider.min.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/main.css">
    
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body, html {
            height: 100%;
            margin: 0;
        }
        .content-wrap {
            min-height: calc(100% - 40px); /* Adjust 40px based on your footer's height */
            margin-bottom: -40px; /* Negative value of footer's height */
        }
        .content-wrap:after {
            content: "";
            display: block;
        } 
        .primary-btn {
            background-color: #007bff;
            border: none;
            color: #fff;
            padding: 1px 20px;
            border-radius: 0; /* Rectangular button */
            cursor: pointer;
            margin-bottom: 10px;
        }
        
        footer, .content-wrap:after {
            height: 40px; /* Footer's height */
        }
        footer {
            background-color: #f8f9fa;
            text-align: center;
            padding: 10px;
            width: 100%;
        }
        
    </style>
</head>
<body>


<div class="container mt-5 form-box">
    <form action="AddProductServlet" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="category">Category:</label>
            <select class="form-control" id="category" name="categoryID" required>
                <!-- Add options here -->
            </select>
        </div>
        <div class="form-group">
            <label for="name">Product Name:</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea class="form-control" id="description" name="description" required></textarea>
        </div>
        <div class="form-group">
            <label for="price">Price:</label>
            <input type="text" class="form-control" id="price" name="price" required>
        </div>
        <div class="form-group">
            <label for="qty">Quantity:</label>
            <input type="number" class="form-control" id="qty" name="qty" required>
        </div>
        <div class="form-group">
            <label for="discount">Discount (%):</label>
            <input type="text" class="form-control" id="discount" name="discount">
        </div>
        <div class="form-group">
            <label for="image">Product Image:</label>
            <input type="file" class="form-control-file" id="image" name="image">
        </div>
        <div class="form-row">
    <div class="form-group col-md-12">
        <button class="primary-btn btn-block" style="color: white;">Add Category</button>
    </div>
        </div>
    </form>
</div>



<script>
    // Get the success parameter from the URL query string
    var urlParams = new URLSearchParams(window.location.search);
    var success = urlParams.get('success');

    if (success === 'true') {
        // Display success toast message
        alert("Product successfully added!");
    }
</script>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
