package com.ecomm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecomm.dao.ProductDao;
import com.ecomm.util.DBConnection;

/**
 * Servlet implementation class RemoveProductServlet
 */
@WebServlet("/RemoveProductServlet")
public class RemoveProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        
        // Assuming ProductDao has a method deleteProduct(int productId)
        ProductDao productDao = new ProductDao(DBConnection.getDbConnection());
        boolean success = productDao.deleteProduct(productId);
        
        if (success) {
            // If product is successfully removed, set an attribute to show in the popup
            request.getSession().setAttribute("message", "Product Removed Successfully!");
        } else {
            request.getSession().setAttribute("message", "Failed to Remove Product.");
        }
        
        // Redirect back to the removeProduct.jsp or relevant page
        response.sendRedirect("removeProduct.jsp");
    }
}
