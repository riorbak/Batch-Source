package com.revature;

public class Scopes {
	static int numOfPuppies; // static variable, lifetime = class lifetime

	//instance
	private int numOfLegs;
	private boolean goodBoye;
	private String nameColor;
	//identifiers
	//begin w/ letters, underscores, or currency characters
	//after first, can include numbers also
	//Alt 156	�	Pound
	//Alt 0128	�	Euro
	//Alt 155	�	Cent
	private int $isadumbvariablename;

	public Scopes(int numOfLegs, boolean goodBoye, 
			String nameColor) {	//the parameter "nameColor" is a method scope
		this.numOfLegs = numOfLegs;		//this.[] is instance scope
		this.goodBoye = goodBoye;
		this.nameColor = nameColor;	//Shadowing = what happens when variables if diff scopes have same identifier
	}

	public static void main(String[] args) {
		System.out.println(numOfPuppies);
	}

	public int methodTest(int a) { // a and test are method variables, lifetime = method
		int test = 3 + a;
		System.out.println(test);
		return test;
	}

	public void loopy() {
		for (int i = 0; i < 5; i++) { // i is a block scope variable, lifetime = loop
			System.out.println(i + " is a number!");
		}
	}

}
