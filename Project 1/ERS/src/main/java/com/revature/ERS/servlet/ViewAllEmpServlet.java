package com.revature.ERS.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.ERS.UserDAOImpl;
import com.revature.ERS.beans.User;

public class ViewAllEmpServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();		
		
		ArrayList<User> usrs = new ArrayList<>();
		          
		try{  	   		
			UserDAOImpl usr = new UserDAOImpl();
			
			usrs = usr.viewEmployees();
			
			for(User u : usrs)
			{
				pw.println("<tr><td>" +u.getuID() + "</td>" +
					"<td>" + u.getUsername() + "</td>" +
					"<td>" + u.getFirstName() + "</td>" + 
					"<td>" + u.getLastName() + "</td>" +
					"<td>" + u.getEmail() + "</td> " + 
					"<td>" + u.getRole() + "</td>");			
			}
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
