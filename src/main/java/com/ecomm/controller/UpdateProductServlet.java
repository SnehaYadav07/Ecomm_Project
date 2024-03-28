package com.ecomm.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.ecomm.dao.CategoryDao;
import com.ecomm.dao.ProductDao;
import com.ecomm.model.Category;
import com.ecomm.model.Product;
import com.ecomm.util.DBConnection;

@WebServlet("/UpdateProductServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class UpdateProductServlet extends HttpServlet {
    
    private static final String UPLOAD_DIRECTORY = "D:\\workspace\\uploads\\productImages"; // Ensure this matches your directory

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        CategoryDao categoryDao = new CategoryDao(DBConnection.getDbConnection());
        ProductDao productDao = new ProductDao(DBConnection.getDbConnection());
        
        try {
            int categoryId = Integer.parseInt(request.getParameter("categoryID"));
            Category category = categoryDao.getCategoryById(categoryId);
            
            int productId = Integer.parseInt(request.getParameter("productId"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            int qty = Integer.parseInt(request.getParameter("qty"));
            double discount = Double.parseDouble(request.getParameter("discount"));
            
            Part filePart = request.getPart("image");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            String filePath = saveFile(filePart, fileName);

            Product product = new Product(productId, category, name, description, price, qty, discount, fileName);
            
            if(productDao.updateProduct(product)) {
                response.sendRedirect("updateProductForm.jsp?success=true");
            } else {
                request.setAttribute("errorMessage", "Failed to update product. Please try again.");
                request.getRequestDispatcher("/updateProductForm.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred. Please try again.");
            request.getRequestDispatcher("/updateProductForm.jsp").forward(request, response);
        }
    }
    
    private String saveFile(Part filePart, String fileName) throws IOException {
        String filePath = UPLOAD_DIRECTORY + File.separator + fileName;
        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Create directory if it doesn't exist
        }
        filePart.write(filePath);
        return filePath;
    }
}
