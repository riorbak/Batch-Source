package com.revature;

import java.util.Arrays;

//Overloading
//The first thing we look for is:
	//Exact Match
	//Conversion -> Primitives/Objects cast themselves to other types.
	//Boxing -> Primitives/Objects will undergo Auto Boxing/UnBoxing
	//Varargs -> arguments wrapped into arrays.
		//Method that will take in a variable number of arguments
		//Vararg must be the last argument, can be of any type

public class Wrapper {
	
	public static void main(String[] args) {
		//conversion
		int a = 4;
		//boxing
		Double objectDouble = 6.6;
		System.out.println(addThis(a));
		System.out.println(addThis(objectDouble));
		//varargs
		method(7,8,3,2,7);
		method(1,2,3,4,5,6,7,7,8,9,3,4,4,2,4,6,7,4,4);
		method(a, objectDouble.intValue(), (int) (double) objectDouble, 12);
		method("@@@@@@@@@@@@@@@", a, objectDouble.intValue(), (int) (double) objectDouble, 12);
		method("@@@@@@@@@@@@@@@");
		
		comparingPrimitivesAndWrappers();
	}
	
	public static double addThis(double deez) {
		return deez+5;
	}
	
	public static void method(int... x) {
		System.out.println(Arrays.toString(x));
	}
	
	public static void method(String s, int...x) {
		System.out.println(s + " " + Arrays.toString(x));
	}
	
	public static void comparingPrimitivesAndWrappers(){
		int intPrimitive = 5;
		Integer intObject = 5;
		short shortPrimitive = 5;
		Short shortObject = 5;
		long longPrimitive = 5L;
		Long longObject = 5l;
		float floatPrimitive = 5.0F;
		Float floatObject = 5.0f;
		double doublePrimitive = 5.0;
		Double doubleObject = 5.0;

		//unwraps the object to match the primitive to the object's value
		System.out.println("intPrimitive==intObject: "
					+(intPrimitive == intObject));
		//Compare memory locations of two objects
		System.out.println("intObject==new Integer(5): "
				+(intObject == new Integer(5)));
		//compare values of two objects
		System.out.println("intObject.equals(new Integer(5)): "
				+(intObject.equals(new Integer(5))));
		//comparing two primitives compares the value, regardless of order
		System.out.println("intPrimitive==shortPrimitive: "
				+(intPrimitive == shortPrimitive));
		System.out.println("shortPrimitive==intPrimitive: "
				+(shortPrimitive == intPrimitive));
		
		//cannot compare two different object types
		System.out.println("longObject.equals(intObject): "
				+(longObject.equals(intObject)));
		//retrieve the value from the wrapper.
		System.out.println("longObject.longValue()==intObject.intValue(): "
							+ (longObject.longValue()==intObject.intValue()));
		
		Integer i= 5;
		Integer s=5;
		Integer q= new Integer(5);
		System.out.println("Integer=5 == Integer=5: "+ (i==s ));
		System.out.println("Integer=5 == new Integer(5): "+ (i==q ));
		
	}
}
