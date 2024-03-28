package com.ecomm.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecomm.dao.RegisterDao;
import com.ecomm.model.User;
import com.ecomm.util.DBConnection;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DBConnection dbConnection;
	private static RegisterDao registerDao;
       
    public RegisterServlet() {               // it will do the Database Connection first.
        super();
        // Load and register the JDBC driver class
        try {
			Class.forName("oracle.jdbc.driver.OracleDriver");    // loading driver manually
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        registerDao = new RegisterDao(DBConnection.getDbConnection());   // here it will go to the overloaded constructor of RegisterDao then call the method create the connection.
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside service using annotation");
		
		String name = request.getParameter("name");              
		String email = request.getParameter("email");
		String password = request.getParameter("password");      // getting parameter from jsp means from box
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String pincode = request.getParameter("pincode");
		String State = request.getParameter("state");
		
		User createUser = new User();          // here it will go to the default constructor then setter method will be called.
		createUser.setName(name);             // here in setter we are passing the string variable which we have created above
		createUser.setEmail(email);
		createUser.setPassword(password);      // setter methods of model class to reflect change in database via jsp(creating new user work in process)
		createUser.setPhone(phone);
		createUser.setGender(gender);
		createUser.setAddress(address);
		createUser.setCity(city);
		createUser.setPincode(pincode);
		createUser.setState(State);
		
		try {
			User user = registerDao.create(createUser);  
			
			// here it will go to the create method of the registerDao and we are passing createUser object which have all the values in it.
			//To call jsp pages include jsp page name in sendRedirect
			response.sendRedirect("login.jsp");              // if it is true it will go to the login page.
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
