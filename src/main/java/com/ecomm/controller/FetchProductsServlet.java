package com.ecomm.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecomm.dao.ProductDao;
import com.ecomm.model.Product;
import com.ecomm.util.DBConnection;

@WebServlet("/fetchProducts")
public class FetchProductsServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao productDao = new ProductDao(DBConnection.getDbConnection());
        
        try {
            List<Product> products = productDao.findAllProducts(); // Fetch products
            request.setAttribute("products", products); // Add to request
            request.getRequestDispatcher("/removeProduct.jsp").forward(request, response); // Forward to JSP
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirect to an error page if an exception occurs
        }
    }
}
