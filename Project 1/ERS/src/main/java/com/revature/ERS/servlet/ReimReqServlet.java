package com.revature.ERS.servlet;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

import com.revature.ERS.beans.Reimbursement;
import com.revature.ERS.UserDAOImpl;

public class ReimReqServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		HttpSession ses = req.getSession(false);
		resp.setContentType("text/html");
		
		UserDAOImpl u = new UserDAOImpl();
		Reimbursement r = new Reimbursement();
		String userName = "";
		Blob blo = null;
		
		if(ses != null)
			userName = ses.getAttribute("username").toString();			
		
		int a = Integer.parseInt(req.getParameter("amt"));
		r.setAmount(a);
		
		int t = Integer.parseInt(req.getParameter("typ"));
		r.setTypeID( t );
		
		r.setDescription(req.getParameter("desc"));
		
//		if(req.getParameter("rcpt") != null)
//		{
//			byte[] b = req.getParameter("rcpt").getBytes();
//			
//			try {
//				blo = new javax.sql.rowset.serial.SerialBlob(b);
//			} catch (SerialException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}			
//		}		
//		
//		if (blo != null)
//			r.setReceipt(blo);
		
		try {
			u.makeRequest(userName, r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if( ((int) req.getAttribute("role")) == 0 )
			resp.sendRedirect("01-EmployeeHome.html");
		else
			resp.sendRedirect("11-ManagerHome.html");
	}
}
