package com.revature.ERS.servlet;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.revature.ERS.UserDAOImpl;

public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		resp.setContentType("text/html");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		UserDAOImpl check = new UserDAOImpl();
		
		try
		{			
			int role = check.login(username, password);
			
			if(role == 0) 
			{
				session.setAttribute("username", username);
				session.setAttribute("problem", null);
				session.setAttribute("role", role);
				
				resp.sendRedirect("01-EmployeeHome.html");
				System.out.println("Employee");
			} 
			else if(role > 0 ) 
			{
				session.setAttribute("username", username);
				session.setAttribute("problem", null);
				session.setAttribute("role", role);
				
				resp.sendRedirect("11-ManagerHome.html");
				System.out.println("Manager");
			}
			else 
			{
				session.setAttribute("problem", "incorrect password");
				resp.sendRedirect("00-login.html");
				System.out.println("Invalid");
			}
		}
		catch (Exception e2) 
		{
			e2.printStackTrace();
		}  

	}

}

