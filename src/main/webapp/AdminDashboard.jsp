<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    
    <!-- CSS -->
    <link rel="stylesheet" href="css/linearicons.css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/themify-icons.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/nice-select.css">
    <link rel="stylesheet" href="css/nouislider.min.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/main.css">
    
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
     a {
            text-decoration: none;
        }
        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            background-color: #f8f9fa;
        }
        .navbar {
            background-color: #343a40;
            color: #ffffff;
           
        }
        .navbar-brand {
            font-weight: bold;
        }
        .navbar-nav .nav-link {
            color: #ffffff;
        }
        main {
            flex: 1;
            display: flex;
        }
        .sidebar {
            margin:120px;
            flex-basis: 350px;
            border-right: 1px solid #ddd;
            padding: 10px;
            background-color: #ffffff;
        }
        .content {
            flex-grow: 1;
            margin:120px;
            padding: 20px;
        }
        footer {
            background-color: #343a40;
            color: #ffffff;
            text-align: center;
            padding: 10px;
        }
        .sidebar h3 {
    text-align: center; 
    margin-bottom: 20px; 
    font-size: 24px;
    font-weight: bold;
    color: #333; 
}
        
        .welcome {
            margin-top: 15px; /* Adjust margin as needed */
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
    </style>
</head>
<body>
<header class="header_area sticky-header">
    <div class="main_menu">
        <nav class="navbar navbar-expand-lg navbar-light main_box">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <a class="navbar-brand logo_h" href="index.jsp">Admin Dashboard</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                 aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                    <div class="welcome">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="nav-item welcome-message">
                           <a class="nav-link" href="#" style="font-weight: bold; font-size: 15px;">
                            Welcome, <%= session.getAttribute("admin") %>
                            </a>   
                        </li>
                        <li class="nav-item">
                            <div class="form-row">
                                <div class="form-group col-md-12">
                                    <a class="primary-btn btn-block" href="logout">Logout</a>
                                </div>
                            </div>
                        </li>
                    </ul>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</header>


    
    <main>
    <div class="sidebar">
        <h3 class="mb-3 ">Manage Products</h3>
        <div class="form-row">
            <div class="form-group col-md-12">
                <button class="primary-btn btn-block" onclick="loadPage('addProduct.jsp')">Add Product</button>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-12">
                <button class="primary-btn btn-block" onclick="loadPage('addCategory.jsp')">Add Category</button>
            </div>
        </div>
        <div class="form-row">
    <div class="form-group col-md-12">
        <button class="primary-btn btn-block" onclick="loadPage('removeProduct.jsp')" style="color: white;">Remove Product</button>
    </div>
</div>
         <div class="form-row">
    <div class="form-group col-md-12">
        <button class="primary-btn btn-block" onclick="loadPage('updateProductForm.jsp')" style="color: white;">Update Product</button>
    </div>
</div>
    </div>
    <div class="content" id="iframeContainer">
        <!-- Form will be loaded here -->
    </div>
</main>

    <footer>
        © 2024 Ecomm. All rights reserved.
    </footer> 

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
    function loadPage(pageUrl) {
        document.getElementById('iframeContainer').innerHTML = '<iframe src="' + pageUrl + '" style="width:100%; height:100%; border:none;"></iframe>';
    }
</script>
    
</body>
</html>
