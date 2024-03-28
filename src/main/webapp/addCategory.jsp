<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Category</title>
    
    <!-- CSS -->
    <link rel="stylesheet" href="css/linearicons.css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/themify-icons.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/nice-select.css">
    <link rel="stylesheet" href="css/nouislider.min.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/main.css">
    
    
    <!-- Bootstrap CSS for styling -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {
            margin-top: 20px;
            overflow: hidden;
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
       .white-text {
        color: white;
    }
       
    </style>
</head>
<body>



<div class="container">
    <% 
        // Check if there's an error message set in the request
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null && !errorMessage.isEmpty()) {
    %>
        <div class="alert alert-danger" role="alert">
            <%= errorMessage %>
        </div>
    <% 
        }
    %>
    <form action="AddCategoryServlet" method="post">
        <div class="form-group">
            <label for="categoryName">Category Name:</label>
            <input type="text" class="form-control" id="categoryName" name="categoryName" required>
        </div>
        <div class="form-group">
            <label for="categoryDescription">Category Description:</label>
            <textarea class="form-control" id="categoryDescription" name="categoryDescription" required></textarea>
        </div>
         <div class="form-row">
    <div class="form-group col-md-12">
        <button class="primary-btn btn-block" style="color: white;">Add Category</button>
    </div>
</div>
        
        
    </form>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
