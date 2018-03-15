package com.revature.ERS.servlet;

import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.revature.ERS.UserDAOImpl;
import com.revature.ERS.beans.Reimbursement;

public class AppDenyServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("DEBUG: App/Deny called");
		HttpSession session = req.getSession();
		resp.setContentType("text/html");
		
		//Failed JSON attempt. Time constraints prevented operation. Will comeback to this.
//		Gson gson = new Gson();
//		String jsonInString = req.getParameter("obj");
//		Reimbursement r = gson.fromJson(jsonInString, Reimbursement.class);
		
		Reimbursement r = new Reimbursement();
		
		r.setId(Integer.parseInt(req.getParameter("reqID")));
		r.setStatusID(Integer.parseInt(req.getParameter("appDen")));
		
		          
		try{  	   		
			UserDAOImpl usr = new UserDAOImpl();
			
			usr.apprDenyReq(session.getAttribute("username").toString(), r.getId(), r.getStatusID());
			
			resp.sendRedirect("13-ViewAllPending.html");
		}
		catch (Exception e2) 
		{
			e2.printStackTrace();
		}  
		          
	}
	
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
