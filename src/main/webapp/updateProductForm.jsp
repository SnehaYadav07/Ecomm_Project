<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ecomm.model.Product" %>
<%@ page import="com.ecomm.dao.ProductDao" %>
<%@ page import="com.ecomm.util.DBConnection" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Product</title>
   <%--  <% 
    String success = request.getParameter("success");
    if("true".equals(success)) { 
    %>
        <div class="alert alert-success" role="alert">
            Product updated successfully!
        </div>
    <% 
    } 
    %> --%>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

 <!-- CSS -->
    <link rel="stylesheet" href="css/linearicons.css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/themify-icons.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/nice-select.css">
    <link rel="stylesheet" href="css/nouislider.min.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/main.css">

<style>
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
<div class="container mt-4">
    <h2>Update Product</h2>
   <%--  <% 
        int productId = Integer.parseInt(request.getParameter("productId"));
        ProductDao productDao = new ProductDao(DBConnection.getDbConnection());
        Product product = productDao.findProductById(productId);
        if(product != null) {
    %> --%>
    <form action="UpdateProductServlet" method="post" enctype="multipart/form-data">
        <input type="hidden" name="productId"
         <%-- value="<%= product.getProductId() %>" --%>
        >
	    <input type="hidden" name="categoryID" 
	    <%-- value="<%= product.getCategory().getCategoryId() %>" --%>
	    >
    
        
        <div class="form-group">
            <label for="name">Product Name:</label>
            <input type="text" class="form-control" id="name" name="name"
             <%-- value="<%= product.getName() %>" --%> 
             required>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea class="form-control" id="description" name="description" required>
            <%-- <%= product.getDescription() %> --%>
            </textarea>
        </div>
        <div class="form-group">
            <label for="price">Price:</label>
            <input type="number" class="form-control" id="price" name="price" 
            <%-- value="<%= product.getPrice() %>"  --%>
            required>
        </div>
        <div class="form-group">
            <label for="qty">Quantity:</label>
            <input type="number" class="form-control" id="qty" name="qty" 
            <%-- value="<%= product.getQty() %>" --%> 
            required>
        </div>
        <div class="form-group">
            <label for="discount">Discount:</label>
            <input type="number" class="form-control" id="discount" name="discount"
             <%-- value="<%= product.getDiscount() %>"  --%>
             required>
        </div>
        <div class="form-group">
            <label for="image">Product Image:</label>
            <input type="file" class="form-control-file" id="image" name="image">
            <img src="/"
            "<%-- <%= request.getContextPath() + "/ImageServlet?imagePath=" + product.getImage() %>" --%> 
            alt="Product Image" height="100" width="100">
        </div>
        <div class="form-row">
    <div class="form-group col-md-12">
        <button class="primary-btn btn-block" style="color: white;">Update Product</button>
    </div>
    </div>
    </form>
   <%--  <% 
        } else {
    %>
        <p>Product not found.</p>
    <% 
        }
    %> --%>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
