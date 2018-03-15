package com.revature.ERS;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnFactory {

	private static ConnFactory cf = null;
	
	private ConnFactory()
	{
		super();		
	}
	
	public static synchronized ConnFactory getInstance()
	{		
		if(cf == null)
			cf = new ConnFactory();
		
		return cf;		
	}
	
	public static Connection getConnection()
	{
		getInstance();
		
		String propFile = "database.properties";
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		
		Connection conn = null;
		
		try (InputStream resourceStream = loader.getResourceAsStream(propFile))
		{
			Properties prop = new Properties();
			prop.load(resourceStream);
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("usr"), prop.getProperty("pwd"));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
				
		return conn;
		
	}	
}
