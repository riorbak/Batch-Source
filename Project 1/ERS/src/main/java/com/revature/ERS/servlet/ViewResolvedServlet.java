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
import com.revature.ERS.beans.Reimbursement;

public class ViewResolvedServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{		
		HttpSession session = req.getSession();
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();		
		
		ArrayList<Reimbursement> data = new ArrayList<>();	
				          
		String uName = (String) session.getAttribute("username");
		int role = (int) session.getAttribute("role");
		          
		try{  	
			if(role == 0)
			{
				UserDAOImpl u = new UserDAOImpl();
				
				data = u.viewResolved(uName);
				
				for(Reimbursement r : data)
				{
					pw.print("<tr><td>" + r.getAmount() + "</td>" + 
							"<td>" + r.getType() + "</td>" +
							"<td>" + r.getDescription() + "</td> " + 
							"<td>" + r.getSubmitted() + "</td>" + 
							"<td>" + r.getResolved() + "</td>" +
							"<td>" + r.getStatus() + "</td>");			
				}
				
				pw.print("</table>");
			}
			else
			{
				UserDAOImpl u = new UserDAOImpl();
				
				data = u.viewAllResolved();
				
				for(Reimbursement r : data)
				{
					pw.print("<tr><td>" + r.getAuthID() + "</td>" +
							"<td>" + r.getAuthor() + "</td>" +
							"<td>" + r.getAmount() + "</td>" + 
							"<td>" + r.getType() + "</td>" +
							"<td>" + r.getDescription() + "</td> " + 
							"<td>" + r.getSubmitted() + "</td>" + 
							"<td>" + r.getResolved() + "</td>" +
							"<td>" + r.getStatus() + "</td>");			
				}
				
				pw.print("</table>");
			}
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
