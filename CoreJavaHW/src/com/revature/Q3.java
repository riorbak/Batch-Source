package com.revature;

import java.util.Scanner;

public class Q3 {
	public void run() {
		System.out.println("Alphabet backwards: "+reverseString("abcdefghijklmnopqrstuvwxyz"));
		Scanner s = new Scanner(System.in);
		System.out.print("Try your own? Type in a String: ");
		System.out.println("Your String backwards = " + reverseString(s.nextLine()));
	}
	
	public String reverseString(String s) {
		String reverse = "";
		for(int i=s.length()-1; i>=0; i--) {
			reverse += s.charAt(i);
		}
		return reverse;
	}
}
