package com.revature.JDBCBank;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class IO 
{	
	private static IO singleton = null;
	
	private IO()
	{
		
	}
	
	public static IO getInstance()
	{
		if(singleton == null)
			singleton = new IO();
		
		return singleton;
	}	
	
	public static void writeObject(Serializable s, String filename)
	{
		try(FileOutputStream fileOut= new FileOutputStream(filename);
				ObjectOutputStream out = new ObjectOutputStream(fileOut))
		{
			out.writeObject(s);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static Serializable readObject(String filename)
	{
		Serializable s = null;
		try(FileInputStream fileIn= new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(fileIn))
		{	
			s= (Serializable) in.readObject();	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public static boolean checkForData(String filename)
	{
		boolean success = true;
		try
		{	
			BufferedReader br = new BufferedReader(new FileReader(filename));     
			if (br.readLine() == null)
			{
				//System.out.println("No errors, and file empty");
				success = false;
			}
			br.close();
		}
			catch (FileNotFoundException e) {
				e.printStackTrace();
		} 	catch (IOException e) {
				e.printStackTrace();
		}
		
		if(success)			
			return true;
		else
			return false;
		
	}
}