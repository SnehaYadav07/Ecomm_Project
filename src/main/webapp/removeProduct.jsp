<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ecomm.model.Product" %>
<%@ page import="com.ecomm.dao.ProductDao" %>
<%@ page import="com.ecomm.util.DBConnection" %>
<!DOCTYPE html>
<html>
<head>
    <title>Remove Product</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h2>Remove or Update Products</h2>
    <% if (session.getAttribute("message") != null) { %>
    <script type="text/javascript">
        alert('<%= session.getAttribute("message") %>');
        <% session.removeAttribute("message"); %>
    </script>
<% } %>
    
    <div class="row">
        <% 
            ProductDao productDao = new ProductDao(DBConnection.getDbConnection());
            List<Product> products = null;
            try {
                products = productDao.findAllProducts();
            } catch(Exception e) {
                e.printStackTrace();
            }
            
            if(products != null && !products.isEmpty()) {
                for(Product product : products) {
        %>
            <div class="col-md-4">
                <div class="card mb-4 shadow-sm">
                    <!-- Adjust image path as necessary -->
					<img src="<%= request.getContextPath() %>/image/<%=product.getImage()%>" class="card-img-top" alt="<%=product.getName()%>">
                    
                    <div class="card-body">
                        <h5 class="card-title"><%=product.getName()%></h5>
                        <p class="card-text"><%=product.getDescription()%></p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <form action="<%=request.getContextPath()%>/RemoveProductServlet" method="post">
    								<input type="hidden" name="productId" value="<%=product.getProductId()%>">
    								<button type="submit" class="btn btn-sm btn-outline-danger">Remove</button>
								</form>

                                <form action="updateProductForm.jsp" method="get">
                                    <input type="hidden" name="productId" value="<%=product.getProductId()%>">
                                    <button type="submit" class="btn btn-sm btn-outline-secondary">Update</button>
                                </form>
                            </div>
                            <small class="text-muted">$<%=product.getPrice()%></small>
                        </div>
                    </div>
                </div>
            </div>
        <% 
                }
            } else {
        %>
            <p>No products found.</p>
        <%
            }
        %>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
