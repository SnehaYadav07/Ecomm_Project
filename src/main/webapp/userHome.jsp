<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ecomm.model.Product" %>    <!-- import statement of java in jsp  -->
<%@ page import="com.ecomm.dao.UserHomeDao" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Home</title>
    <style>
        /* Add your CSS styles here */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 1200px;
            margin: 20px auto;
            padding: 20px;
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .welcome {
            font-size: 24px;
            margin-bottom: 20px;
        }
        .dashboard {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }
        .card {
            flex: 1 1 300px;
            background-color: #fff;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .card h2 {
            font-size: 20px;
            margin-top: 0;
        }
        .card p {
            margin: 10px 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <p class="welcome">Welcome <%= (String) session.getAttribute("user") %></p>
        <div class="dashboard">
        
            <!-- Display products dynamically -->  
            <!--  retrieving list of products from the request attribute named "products" and also type cast to list of object-->
           <% 
            try {
                UserHomeDao userHomeDao = new UserHomeDao();
                List<Product> products = userHomeDao.findAll();
                if(products != null && !products.isEmpty()) {
                    for (Product product : products) { 
            %>
                    <div class="card"> <!-- card like UI Component -->
                        <h2><%= product.getName() %></h2>
                        <!-- img tag for image and alt for the alternative text for the image -->
                        <img src="<%= product.getImage() %>" alt="<%= product.getName() %> Image" width="200" height="200">
                        
                        <p><%= product.getDescription() %></p>
                        <p>Price: $<%= product.getPrice() %></p>
                        <p>Quantity: <%= product.getQty() %></p>
                        <p>Discount: <%= product.getDiscount() %>%</p>
                    </div>
            <% 
                    }
                } else {
                    out.println("No products available."); // Output message if no products found
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.println("An error occurred while fetching products."); // Output error message
            }
            %>
        </div>
    </div>
</body>
</html>
