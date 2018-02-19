package com.revature;

import com.revature.floatone.FirstFloat;

//Q11. Write a program that would access two float-variables from a class 
// that exists in another package. Note, you will need to create two 
// packages to demonstrate the solution. 
public class Q11 extends FirstFloat{

	public void runQ11()
	{
		FirstFloat f = new FirstFloat();
		
		System.out.println("float x = " + f.getX());
		System.out.println("float y = " + f.getY());
	}
}
