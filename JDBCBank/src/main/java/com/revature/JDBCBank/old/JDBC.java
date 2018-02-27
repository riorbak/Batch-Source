package com.revature.JDBCBank.old;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBC 
{
	private static JDBC single_instance = null;
	
	private static String propFile = "files\\old\\DBProperties.txt";
	private static String driver;
	private static String url;
	private static String user;
	private static String pwd;
	
	private static Connection connected = null;
	
	private JDBC()
	{
		
	}
	
	public static JDBC getInstance()
	{
		if (single_instance == null)
			single_instance = new JDBC();
		
		return single_instance;
	}

	public void startConnection()
	{
		readPropertiesFile();
		
		 System.out.println("-------- Connecting to Bank Database... ------");

        try {

            Class.forName(driver);

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;

        }

        System.out.println("Oracle JDBC Driver Registered!");

        

        try {

            connected = DriverManager.getConnection(url, user, pwd);

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (connected != null) {
            System.out.println("Database connection established.");
        } else {
            System.out.println("Failed to make connection!");
        }
	}	
	
	public void readPropertiesFile()
	{		
		ArrayList<String> prop = new ArrayList<>();
		
		try {
			File file = new File(propFile);
            BufferedReader b = new BufferedReader(new FileReader(file));

            String readLine = "";

            while ((readLine = b.readLine()) != null) 
            {
                prop.add(readLine);
            }
            
    		b.close();
            
        }catch(FileNotFoundException e) {
        	e.printStackTrace();
        }		
		catch (IOException e) {
            e.printStackTrace();
        }
		
		for(String s : prop)
		{
			if(s.contains("driver="))
				setDriver(s.substring(s.indexOf("=")+1, s.length()));
			else if(s.contains("url="))
				setUrl(s.substring(s.indexOf("=")+1, s.length()));
			else if(s.contains("usr="))
				setUser(s.substring(s.indexOf("=")+1, s.length()));
			else if(s.contains("pwd="))
				setPwd(s.substring(s.indexOf("=")+1, s.length()));
		}	
		
		//Debug code to check string manipulation
		//System.out.println(driver + "\n" + url + "\n" + user + "\n" + pwd);

	}
	
	public void closeConnection()
	{
		try {
			connected.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getDriver() {
		return driver;
	}
	public static void setDriver(String driver) {
		JDBC.driver = driver;
	}

	public static String getUrl() {
		return url;
	}
	public static void setUrl(String url) {
		JDBC.url = url;
	}

	public static String getUser() {
		return user;
	}
	public static void setUser(String user) {
		JDBC.user = user;
	}

	public static String getPwd() {
		return pwd;
	}
	public static void setPwd(String pwd) {
		JDBC.pwd = pwd;
	}

	
}
