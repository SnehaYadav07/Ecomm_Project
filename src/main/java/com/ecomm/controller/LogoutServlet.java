package com.ecomm.controller;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

@WebServlet("/logout") 
public class LogoutServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L; 

	public LogoutServlet() { 
		super(); 
	} 

	// doGet() method 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 

		// Get the print writer object to write into the response 
		PrintWriter out = response.getWriter(); 

		// Set the content type of response to "text/html" 
		response.setContentType("text/html"); 

		// For understanding purpose, print the session object in the console before 
		// invalidating the session. 
		System.out.println("Session before invalidate: "+ request.getSession(false)); 

		// Invalidate the session. 
		request.getSession(false).invalidate(); 

		// Print the session object in the console after invalidating the session. 
		System.out.println("Session after invalidate: "+ request.getSession(false)); 

		// Print success message to the user and close the print writer object. 
		out.println("Thank you! You are successfully logged out."); 
		out.close(); 
	} 

} 
