package com.revature.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializeDemo {
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
			s = (Serializable) in.readObject();	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return s;
	}
}
