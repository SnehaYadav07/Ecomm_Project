package com.ecomm.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecomm.dao.LoginDao;
import com.ecomm.model.Admin;
import com.ecomm.model.User;
import com.ecomm.util.DBConnection;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DBConnection dbConnection;
	private static LoginDao loginDao;

    public LoginServlet() {
        super();
        //Load and register the JDBC driver class
        try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        loginDao = new LoginDao(DBConnection.getDbConnection());
    }
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		
		User createUser;
		Admin createAdmin;
		try {
			
			// User authentication
			if( "user".equals(role)) {         // for comparing String use this method
				
				createUser = loginDao.authenticateUser(email, password);
				
				if(createUser != null) {
				
				//Redirect to User dashboard
				HttpSession session = request.getSession();
				
				session.setAttribute("user", createUser.getName());
				
				request.getRequestDispatcher("/userHome.jsp").include(request, response);
				// for going to jsp page need to use sendredirect  method    // it will get all data from frontend as a request take to the backend and get the response from there
			                                                               // for using servlet need to use getRequestDispatcher  method
				}else {
					
					// authentication failed for both admin and user
					// Redirect back to login page with error message
					request.getRequestDispatcher("/LoginServlet").include(request, response);
				}
			}
			if( "admin".equals(role)){
			    
				// Admin authentication
				createAdmin = loginDao.authenticateAdmin(email, password);
				
//				System.out.println("------------>"+createAdmin);
//				System.out.println("------------>"+createAdmin.getName());
				
				if(createAdmin != null) {
//					System.out.println("admin is here");
					
					// Redirect to Admin Dashboard
					
					HttpSession session = request.getSession();
					
					session.setAttribute("admin", createAdmin.getName());
					request.getRequestDispatcher("/adminHome.jsp").include(request, response);    // we have to give the similar annotation in that file 

				}else {
					
					// authentication failed for both admin and user
					// Redirect back to login page with error message
					request.getRequestDispatcher("/LoginServlet").include(request, response);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
