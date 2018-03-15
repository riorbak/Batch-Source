package com.revature.ERS.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.ERS.UserDAOImpl;
import com.revature.ERS.beans.User;

public class UpdateInfoServlet extends HttpServlet 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		resp.setContentType("text/html");	
		
		User u = new User();
				          
		String uName = (String) session.getAttribute("username");
		          
		try{  	   		
			UserDAOImpl usr = new UserDAOImpl();
			
			if(req.getParameter("firstName") != null)
				u.setFirstName(req.getParameter("firstName"));
			else
				u.setFirstName(null);
			
			if(req.getParameter("lastName") != null)
				u.setLastName(req.getParameter("lastName"));
			else
				u.setLastName(null);
			
			if(req.getParameter("eMail") != null)
				u.setEmail(req.getParameter("eMail"));
			else
				u.setEmail(null);
			
			u.setUsername(uName);
			
			usr.updateInfo(u);
			
			resp.sendRedirect("05-ViewPersonalInfo.html");
		}
		catch (Exception e2) 
		{
			e2.printStackTrace();
		}  
		          
	}
}
