package com.revature.core.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author johne
 * 
Q20. Create a notepad file called Data.txt and enter the following:

Mickey:Mouse:35:Arizona
Hulk:Hogan:50:Virginia
Roger:Rabbit:22:California
Wonder:Woman:18:Montana
 
Write a program that would read from the file and print it out to the screen in the following format:
 
Name: Mickey Mouse
Age: 35 years
State: Arizona State

 */
public class Q20_Notepad {
	
	public static void main(String[] args) {
		File file = new File("Data.txt");
		Scanner input = null;
		
		String firstName = "";
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		/**
		 * as long as there are lines left to be read,
		 * read the next line
		 */
		while(input.hasNext()) {
			firstName = input.next();
			System.out.println(firstName);
		}
		input.close();
	}
	
}
