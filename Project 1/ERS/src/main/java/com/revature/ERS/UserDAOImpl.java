package com.revature.ERS;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.ERS.beans.Reimbursement;
import com.revature.ERS.beans.User;

public class UserDAOImpl extends User implements UserDAO 
{
	private static final long serialVersionUID = 1L;

	public int login(String username, String password) throws SQLException
	{		
		Connection conn = null;
		PreparedStatement findUsr = null;
		
		try {			
			conn = ConnFactory.getConnection();			
			findUsr = conn.prepareStatement("SELECT UR_ID FROM ERS_USERS WHERE U_USERNAME = ? AND U_PASSWORD = ?");
			
			findUsr.setString(1, username);
			findUsr.setString(2, password);			
 
			ResultSet rs = findUsr.executeQuery();	
			
			while(rs.next())
			{
				int role = rs.getInt(1);
				
//				System.out.println("User role: " + role);
				
				return role;
			}			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("An SQL exception occurred 503");
		} 
		finally 
		{
			if (findUsr != null)
				findUsr.close();
			if (conn != null)
				conn.close();			
		}
		
//		System.out.println("User not found");		
		return -1;
	}

	public void makeRequest(String username, Reimbursement r) throws SQLException 
	{		
		ConnFactory.getInstance();
		Connection conn = null;
		CallableStatement s = null;
		String statement = "{CALL NEW_REIM_REQ(?,?,?,?,?)}";
		
		try {			
			conn = ConnFactory.getConnection();			
			s = conn.prepareCall(statement);
			
			
			s.setInt(1, r.getAmount());
			s.setString(2, r.getDescription());
			s.setBlob(3, r.getReceipt());
			s.setString(4, username);			
			s.setInt(5, r.getTypeID());			

			s.execute();			
			
		} catch (SQLException e) {
			System.out.println("An SQL exception occurred 503");
		} finally {
			if (s != null)
				s.close();
			if (conn != null)
				conn.close();
		}
		
	}


	public ArrayList<Reimbursement> viewPending(String username) throws SQLException 
	{
		Connection conn = null;
		PreparedStatement findPend = null;
		ResultSet rs = null;
		ArrayList<Reimbursement> pending = new ArrayList<>();
		
		try {			
			conn = ConnFactory.getConnection();			
			findPend = conn.prepareStatement("SELECT ERS_REIMBURSEMENTS.R_AMOUNT, ERS_REIMBURSEMENT_TYPE.RT_TYPE, "
					+ "ERS_REIMBURSEMENTS.R_DESCRIPTION, ERS_REIMBURSEMENTS.R_SUBMITTED, ERS_REIMBURSEMENTS.R_RECEIPT "
					+ "FROM ERS_REIMBURSEMENTS "
					+ "LEFT JOIN ERS_REIMBURSEMENT_TYPE ON ERS_REIMBURSEMENTS.RT_TYPE = ERS_REIMBURSEMENT_TYPE.RT_ID "
					+ "WHERE RT_STATUS = 0 AND U_ID_AUTHOR = (SELECT U_ID FROM ERS_USERS WHERE U_USERNAME = ?)") ;
			
			findPend.setString(1, username);		
 
			rs = findPend.executeQuery();			
			
			while(rs.next())
			{
				Reimbursement r = new Reimbursement();
				
				r.setAmount(rs.getInt(1));
				r.setType(rs.getString(2));
				r.setDescription(rs.getString(3));
				r.setSubmitted(rs.getTimestamp(4));
				if(rs.getBlob(5) != null)
					r.setReceipt(rs.getBlob(5));
				
				pending.add(r);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("An SQL exception occurred 503");
		} 
		finally 
		{
			if (findPend != null)
				findPend.close();
			if (conn != null)
				conn.close();			
		}
		return pending;
		
	}

	public ArrayList<Reimbursement> viewResolved(String username) throws SQLException 
	{
		Connection conn = null;
		PreparedStatement findPend = null;
		ResultSet rs = null;
		ArrayList<Reimbursement> resolved = new ArrayList<>();
		
		try {			
			conn = ConnFactory.getConnection();			
			findPend = conn.prepareStatement("SELECT ERS_REIMBURSEMENTS.R_AMOUNT, ERS_REIMBURSEMENT_TYPE.RT_TYPE, ERS_REIMBURSEMENTS.R_DESCRIPTION, " + 
					"ERS_REIMBURSEMENTS.R_SUBMITTED, ERS_REIMBURSEMENTS.R_RESOLVED, ERS_REIMBURSEMENT_STATUS.RS_STATUS FROM ERS_REIMBURSEMENTS " + 
					"LEFT JOIN ERS_REIMBURSEMENT_TYPE ON ERS_REIMBURSEMENTS.RT_TYPE = ERS_REIMBURSEMENT_TYPE.RT_ID " + 
					"LEFT JOIN ERS_REIMBURSEMENT_STATUS ON ERS_REIMBURSEMENTS.RT_STATUS = ERS_REIMBURSEMENT_STATUS.RS_ID " + 
					"WHERE RT_STATUS != 0 AND U_ID_AUTHOR = (SELECT U_ID FROM ERS_USERS WHERE U_USERNAME = ?)");
			
			findPend.setString(1, username);		
 
			rs = findPend.executeQuery();			
			
			while(rs.next())
			{
				Reimbursement r = new Reimbursement();
				
				r.setAmount(rs.getInt(1));
				r.setType(rs.getString(2));
				r.setDescription(rs.getString(3));
				r.setSubmitted(rs.getTimestamp(4));
				r.setResolved(rs.getTimestamp(5));
				r.setStatus(rs.getString(6));				
				
				resolved.add(r);
			}
			 
		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("An SQL exception occurred 503");
		} 
		finally 
		{
			if (findPend != null)
				findPend.close();
			if (conn != null)
				conn.close();			
		}
		return resolved;
		
	}

	public User viewInfo(String username) throws SQLException 
	{
		Connection conn = null;
		PreparedStatement findUsr = null;
		ResultSet rs = null;
		User u = new User();
		
		try {			
			conn = ConnFactory.getConnection();			
			findUsr = conn.prepareStatement("SELECT ERS_USERS.U_FIRSTNAME, ERS_USERS.U_LASTNAME, ERS_USERS.U_EMAIL, "
					+ "ERS_USER_ROLES.UR_ROLE FROM ERS_USERS "
					+ "LEFT JOIN ERS_USER_ROLES ON ERS_USERS.UR_ID = ERS_USER_ROLES.UR_ID "
					+ "WHERE U_USERNAME = ?");
			
			findUsr.setString(1, username);		
 
			rs = findUsr.executeQuery();	
			
			while(rs.next())
			{
				u.setFirstName(rs.getString(1));
				u.setLastName(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setRole(rs.getString(4));
			}			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("An SQL exception occurred 503");
		} 
		finally 
		{
			if (findUsr != null)
				findUsr.close();
			if (conn != null)
				conn.close();			
		}
		
		return u;
		
	}

	public void updateInfo(User u) throws SQLException
	{
		System.out.println("DEBUG: updateInfo called");
		ConnFactory.getInstance();
		Connection conn = null;
		CallableStatement s = null;
		String statement = "{CALL UPDATE_USER(?,?,?,?)}";
		
		try {			
			conn = ConnFactory.getConnection();			
			s = conn.prepareCall(statement);
			
			s.setString(1, u.getFirstName());
			s.setString(2, u.getLastName());
			s.setString(3, u.getEmail());
			s.setString(4, u.getUsername());
			
			s.execute();			
			
		} catch (SQLException e) {
			System.out.println("An SQL exception occurred 503");
		} finally {
			if (s != null)
				s.close();
			if (conn != null)
				conn.close();
		}
		
	}


	public void apprDenyReq(String username, int reqID, int apDn) throws SQLException
	{
		ConnFactory.getInstance();
		Connection conn = null;
		CallableStatement s = null;
		String statement = "{CALL APP_DENY(?,?,?)}";
		
		try {			
			conn = ConnFactory.getConnection();			
			s = conn.prepareCall(statement);
			
			s.setInt(1, reqID);
			s.setInt(2, apDn);
			s.setString(3, username);
			
			s.execute();			
			
		} catch (SQLException e) {
			System.out.println("An SQL exception occurred 503");
		} finally {
			if (s != null)
				s.close();
			if (conn != null)
				conn.close();
		}
		
	}

	public ArrayList<Reimbursement> viewAllPending() throws SQLException 
	{
		Connection conn = null;
		PreparedStatement findPend = null;
		ResultSet rs = null;
		ArrayList<Reimbursement> pending = new ArrayList<>();
		
		try {			
			conn = ConnFactory.getConnection();			
			findPend = conn.prepareStatement("SELECT ERS_REIMBURSEMENTS.R_AMOUNT, ERS_REIMBURSEMENT_TYPE.RT_TYPE, ERS_USERS.U_USERNAME, "
					+ "ERS_REIMBURSEMENTS.R_ID, ERS_REIMBURSEMENTS.R_DESCRIPTION, ERS_REIMBURSEMENTS.R_SUBMITTED, ERS_REIMBURSEMENTS.R_RECEIPT "
					+ "FROM ERS_REIMBURSEMENTS "
					+ "LEFT JOIN ERS_REIMBURSEMENT_TYPE ON ERS_REIMBURSEMENTS.RT_TYPE = ERS_REIMBURSEMENT_TYPE.RT_ID "
					+ "LEFT JOIN ERS_USERS ON ERS_REIMBURSEMENTS.U_ID_AUTHOR = ERS_USERS.U_ID WHERE RT_STATUS = 0 "
					+ "ORDER BY ERS_REIMBURSEMENTS.R_ID");	
 
			rs = findPend.executeQuery();			
			
			while(rs.next())
			{
				Reimbursement r = new Reimbursement();
				
				r.setAmount(rs.getInt(1));
				r.setType(rs.getString(2));
				r.setAuthor(rs.getString(3));
				r.setId(rs.getInt(4));
				r.setDescription(rs.getString(5));
				r.setSubmitted(rs.getTimestamp(6));
				if(rs.getBlob(7) != null)
					r.setReceipt(rs.getBlob(7));
				
				pending.add(r);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("An SQL exception occurred 503");
		} 
		finally 
		{
			if (findPend != null)
				findPend.close();
			if (conn != null)
				conn.close();			
		}
		return pending;
		
	}

	public ArrayList<Reimbursement> viewAllResolved() throws SQLException
	{
		Connection conn = null;
		PreparedStatement findPend = null;
		ResultSet rs = null;
		ArrayList<Reimbursement> resolved = new ArrayList<>();
		
		try {			
			conn = ConnFactory.getConnection();			
			findPend = conn.prepareStatement("SELECT ERS_REIMBURSEMENTS.R_AMOUNT, ERS_REIMBURSEMENT_TYPE.RT_TYPE, ERS_USERS.U_USERNAME, "
					+ "ERS_REIMBURSEMENTS.R_ID, ERS_REIMBURSEMENTS.R_DESCRIPTION, ERS_REIMBURSEMENTS.R_SUBMITTED, "
					+ "ERS_REIMBURSEMENTS.R_RESOLVED, ERS_REIMBURSEMENT_STATUS.RS_STATUS "
					+ "FROM ERS_REIMBURSEMENTS "
					+ "LEFT JOIN ERS_REIMBURSEMENT_TYPE ON ERS_REIMBURSEMENTS.RT_TYPE = ERS_REIMBURSEMENT_TYPE.RT_ID "
					+ "LEFT JOIN ERS_USERS ON ERS_REIMBURSEMENTS.U_ID_AUTHOR = ERS_USERS.U_ID "
					+ "LEFT JOIN ERS_REIMBURSEMENT_STATUS ON ERS_REIMBURSEMENTS.RT_STATUS = ERS_REIMBURSEMENT_STATUS.RS_ID "
					+ "WHERE RT_STATUS != 0");	
 
			rs = findPend.executeQuery();			
			
			while(rs.next())
			{
				Reimbursement r = new Reimbursement();
				
				r.setAmount(rs.getInt(1));
				r.setType(rs.getString(2));
				r.setAuthor(rs.getString(3));
				r.setId(rs.getInt(4));
				r.setDescription(rs.getString(5));
				r.setSubmitted(rs.getTimestamp(6));
				r.setResolved(rs.getTimestamp(7));
				r.setStatus(rs.getString(8));
				
				//receipts
//				if(rs.getBlob(7) != null)
//					r.setReceipt(rs.getBlob(7));
				
				resolved.add(r);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("An SQL exception occurred 503");
		} 
		finally 
		{
			if (findPend != null)
				findPend.close();
			if (conn != null)
				conn.close();			
		}
		return resolved;
	}

	public ArrayList<User> viewEmployees() throws SQLException 
	{
		Connection conn = null;
		PreparedStatement viewEmp = null;
		
		ArrayList<User> users = new ArrayList<>();
		
		String allEmpSt = "SELECT ERS_USERS.U_ID, ERS_USERS.U_USERNAME, ERS_USERS.U_FIRSTNAME, ERS_USERS.U_LASTNAME, ERS_USERS.U_EMAIL, " 
				+ "ERS_USER_ROLES.UR_ROLE FROM ERS_USERS "
				+ "LEFT JOIN ERS_USER_ROLES ON ERS_USERS.UR_ID = ERS_USER_ROLES.UR_ID "
				+ "ORDER BY ERS_USERS.U_ID";
		
		try 
		{			
			conn = ConnFactory.getConnection();			
			viewEmp = conn.prepareStatement(allEmpSt);
 
			ResultSet rs = viewEmp.executeQuery();	
			
			while(rs.next())
			{
				User u = new User();
				
				u.setuID(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setFirstName(rs.getString(3));
				u.setLastName(rs.getString(4));
				u.setEmail(rs.getString(5));	
				u.setRole(rs.getString(6));
				
				users.add(u);
			}
		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("An SQL exception occurred 503");
		} 
		finally 
		{
			if (viewEmp != null)
				viewEmp.close();
			if (conn != null)
				conn.close();			
		}
		
		return users;
	}

	public void viewPending(User u) throws SQLException
	{
		// TODO Auto-generated method stub
		
	}
}
