package com.revature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*Q20. Create a notepad file called Data.txt and enter the following:
 * Mickey:Mouse:35:Arizona
 * Hulk:Hogan:50:Virginia
 * Roger:Rabbit:22:California
 * Wonder:Woman:18:Montana
 * 
 * Write a program that would read from the file and print it out to the screen in the following format:
 * Name: Mickey Mouse
 * Age: 35 years
 * State: Arizona State 
 */
public class Q20 
{
	private static final String inFile = "Data.txt";					//name of input file
	
	ArrayList<FamousPerson> BList = new ArrayList<FamousPerson>();		
	
	public void displayDataFile()
	{
		getFileData();					
		
		for(FamousPerson p : BList)
		{
			//output display
			System.out.println("Name: " + p.first + " "+ p.last + 
					"\nAge: " + p.age + " years" +
					"\nState: " + p.state + " State");
		}
	}
	
	public void getFileData()
	{		
		String unMod = readInputStreamContents();					//get pure data from file
		
		unMod = unMod.replace("\r\n", ":").replace("\n", ":");		//replace the line breaks with ':'
		
		String[] mod = unMod.split(":");							//split string into String[] and remove all ':'
		
		convertFileData(mod);										//use Str[] to make FamousPerson objs
	}
	
	public void convertFileData(String[] strAr)
	{	
		Queue<String> toBeStored = new LinkedList<String>();		//used to store each string (and practice Java Queues)
		
		for (String s : strAr)										//fill up the queue
			toBeStored.add(s);		
		
		//System.out.println(toBeStored.size());							//Debug Code
		
		String f, l, a, s;											//variables for Famous Person Construction
		
		while(!toBeStored.isEmpty())
		{		
			f = toBeStored.remove();								//store variables and remove them from the top/head of queue
			l = toBeStored.remove();
			a = toBeStored.remove();
			s = toBeStored.remove();
			
			BList.add(new FamousPerson(f,l,a,s));					//Add new FamousPerson to BList
		}
	}
	
	public String readInputStreamContents()
	{
		File file = new File(inFile);								//open "Data.txt"
		InputStream is = null;										//set up input stream
		StringBuilder sb = new StringBuilder();						//start up String Builder
		
		
		try {
			is = new FileInputStream(file);							//try to open input stream
		} catch (FileNotFoundException e) {
			e.printStackTrace();									//print out error if caught
		}
		
		int b = 0;		
		
		try {														//try to read from file
			while ((b = is.read()) != -1)							//while not at the end of the stream	
			{
				char c = (char) b;
				sb.append(c);										//append character to string builder
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		if(is != null)												//if input stream initialized
		{
			try {
				is.close();											//try to close it
			} catch (IOException e) {
				e.printStackTrace();								//print out if any exceptions are caught
			}
		}
		
		return sb.toString();										//return the data as a string;
	}
	
	class FamousPerson
	{
		String first, last, age, state;
		
		FamousPerson()
		{
			super();
		}
		
		FamousPerson(String f, String l, String a, String s)
		{
			super();
			first = f;
			last = l;
			age = a;
			state = s;
		}
	}
	
}
