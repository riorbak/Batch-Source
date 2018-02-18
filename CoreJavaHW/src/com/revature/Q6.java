package com.revature;

import java.util.Scanner;

public class Q6 {
	public void run() {
		System.out.println("0 is "+isEvenOrOdd(0)+".");		//print test cases
		System.out.println("1 is "+isEvenOrOdd(1)+".");
		System.out.println("2 is "+isEvenOrOdd(2)+".");
		System.out.println("651 is "+isEvenOrOdd(651)+".");
		System.out.println("6534 is "+isEvenOrOdd(6534)+".");
		System.out.println("-2 is "+isEvenOrOdd(-2)+".");
		System.out.println("-1 is "+isEvenOrOdd(-1)+".");
		System.out.println("-264 is "+isEvenOrOdd(-264)+".");
		System.out.println("-125 is "+isEvenOrOdd(-125)+".");
		System.out.print("Is your number even or odd? Try out an integer (~ -2.147 to 2.147 billion): ");
		Scanner s = new Scanner(System.in);
		int userInput = s.nextInt();
		System.out.println(userInput + " is " + isEvenOrOdd(userInput)+".");
	}
	
	public String isEvenOrOdd(int i) {
		if(i>2) {		//for positive numbers
			while(i>2) {	//do this until we get i <= 2
				i -= 2;	//subtract 2 from i
			}
		}
		else if(i<-2) {	//for negatives
			while(i<-2) {	//do this until we get i >= -2
				i += 2;	//add 2 to i
			}
		}
		if(i==1 || i==-1) {	
			return "odd";
		}
		else if(i==2 || i==-2) {
			return "even";
		}
		return "even, technically";	//edge case for if 0 is put in
	}
}
