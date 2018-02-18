package com.revature.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IO {
	private static final String inFile = "in.txt";
	private static final String outfile = "output.txt";
	
	public void writeOutputStreamContents(String contents) {
		OutputStream os = null;
		File file = new File(outfile);
		try {
			file.delete();	//resets the file, it's remade later when writing into it
			os = new FileOutputStream(file, true);
			os.write(contents.getBytes());
			if(os!=null) {
				os.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String readInputStreamContents() {
		File file = new File(inFile);
		InputStream is = null;
		StringBuilder s = new StringBuilder();
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int b = 0;
		try {
			while((b=is.read()) != -1) {
				char c = (char) b;
				s.append(c);
			}
			if(is!=null) {
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return s.toString();
	}
}
