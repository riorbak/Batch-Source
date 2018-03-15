package com.revature.ERS.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.revature.ERS.UserDAOImpl;
import com.revature.ERS.beans.User;

public class UserInfoServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();		
		
		User u = new User();
				          
		String uName = (String) session.getAttribute("username");
		          
		try{  	   		
			UserDAOImpl usr = new UserDAOImpl();
			
			u = usr.viewInfo(uName);
			
			pw.print("<tr><td>" + u.getFirstName() + "</td>" + 
					"<td>" + u.getLastName() + "</td>" +
					"<td>" + u.getEmail() + "</td> " + 
					"<td>" + u.getRole() + "</td>");			
			
			pw.print("</table>");
		}
		catch (Exception e2) 
		{
			e2.printStackTrace();
		}  
		          
		finally
		{
			pw.close();
		}  

		pw.close();
	}

}
