package com.ecomm.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import com.ecomm.dao.CategoryDao;
import com.ecomm.dao.ProductDao;
import com.ecomm.model.Category;
import com.ecomm.model.Product;
import com.ecomm.util.DBConnection;


import javax.servlet.http.*;

@WebServlet("/AddProductServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class AddProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDao productDao;
    private CategoryDao categoryDao;
    private static final String UPLOAD_DIRECTORY = "D:\\workspace\\uploads\\productImages";

    public void init() {
        productDao = new ProductDao(DBConnection.getDbConnection());
        categoryDao = new CategoryDao(DBConnection.getDbConnection());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve form data
            int categoryId = Integer.parseInt(request.getParameter("categoryID"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            int qty = Integer.parseInt(request.getParameter("qty"));
            double discount = Double.parseDouble(request.getParameter("discount"));

            // Process the file upload
            Part filePart = request.getPart("image");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String filePath = saveFile(filePart, fileName);

            // Create a Product object
            Product product = new Product(new Category(categoryId), name, description, price, qty, discount, fileName); 

            // Persist the product
            if (productDao.addProduct(product)) {
                // Redirect with success message
                response.sendRedirect("addProduct.jsp?success=true");
            } else {
                // Error handling
                request.setAttribute("errorMessage", "Failed to add product. Please try again.");
                request.getRequestDispatcher("/addProduct.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Error handling
            request.setAttribute("errorMessage", "An error occurred while processing your request.");
            request.getRequestDispatcher("/addProduct.jsp").forward(request, response);
        }
    }

    // Method to save file to server
 // Method to save file to server
    private String saveFile(Part filePart, String fileName) throws IOException {
        String filePath = UPLOAD_DIRECTORY + File.separator + fileName;
        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Create the directory if it doesn't exist
        }
        filePart.write(filePath);
        return filePath;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Category> categories = categoryDao.getAllCategories(); // Assuming this method exists and fetches categories
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/addProduct.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirect to an error page in case of failure
        }
    }
}
