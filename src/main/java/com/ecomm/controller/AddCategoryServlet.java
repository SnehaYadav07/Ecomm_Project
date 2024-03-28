package com.ecomm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecomm.dao.CategoryDao;
import com.ecomm.model.Category;
import com.ecomm.util.DBConnection;

/**
 * Servlet implementation class AddCategoryServlet
 */
@WebServlet("/AddCategoryServlet")
public class AddCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String categoryName = request.getParameter("categoryName");
        String categoryDescription = request.getParameter("categoryDescription");

        // Create a new Category object
        Category category = new Category(categoryName, categoryDescription);

        // Instantiate the CategoryDao to interact with the database
        CategoryDao categoryDao = new CategoryDao(DBConnection.getDbConnection());
        
        // Attempt to add the category and capture the result
        boolean isAdded = categoryDao.addCategory(category);

        if (isAdded) {
            // Redirect to the admin home page or a success page if the category was successfully added
            response.sendRedirect("adminHome.jsp"); // Adjust the redirect URL as necessary
        } else {
            // Handle the failure case, perhaps by redirecting back to the form with an error message
            request.setAttribute("errorMessage", "Failed to add the category. Please try again.");
            request.getRequestDispatcher("addCategory.jsp").forward(request, response);
        }	}

}
