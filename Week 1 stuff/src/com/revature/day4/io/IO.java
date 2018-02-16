package com.revature.day4.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IO {

	public void writeOutputStreamContents(String contents) {
		OutputStream os = null;
		File file = new File("output.txt");
		
		try {
			os = new FileOutputStream(file, true);
			os.write(contents.getBytes());
			
			if(os!=null) {
				os.close();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
						
		
	}
	
	public String readInputStreamContents() {
		File file = new File("input.txt");
		InputStream is = null;
		StringBuilder s = new StringBuilder();
		
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			//Todo:
			e.printStackTrace();
		}
		int b = 0;
		try {
			while((b=is.read())!=1) {
				char c = (char) b;
				s.append(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(is!=null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return s.toString();
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
