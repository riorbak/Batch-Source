package com.revature;

import java.lang.String;

//Q3. Reverse a string without using a temporary variable. 
//  Do NOT use reverse() in the StringBuffer or the StringBuilder APIs.
public class Q3 {

	//String variables
	private String strToRev = "Exclusive OR, FTW!";
	
	public void revString()
	{	
		System.out.println("Original String: " + strToRev);	
		
		/* Found this on StackOverflow. Have no idea how it works, but it does.
		 *  So yeah... gonna try something else as well.
		 *  I think its supposed to make 3 substrings each iteration of the loop
		 *   which isolates and swaps the two values on the end, where each loop
		 *   iteration moves inward. The int values of 0 and 1 are throwing me off.
		 *   I'll update if I figure it out.
		for(int i = 0; i < str.length(); i++)								//Test String: "Testing is fun!"
		{																	// 
			str = str.substring( 1, (str.length() - i) )					// 
			        + str.substring(0, 1)									// 
			        + str.substring( (str.length() - i), str.length() );	// 
		}
		*/		
		
		// The following made more sense to me as a C++ programmer. It does bend the rules slightly, as it
		//  uses a storage variable, but that is due to extracting the char[] from the 
		
		char[] ch = strToRev.toCharArray();	//extract the char[] from the string and store it
		int end = ch.length - 1;
		
		//for each iteration, move the start and end points toward the center
		for(int i = 0; i < end; i++, end--)
		{
										//lots of XOR Boolean algebra coming: 
										//              
			ch[i] ^= ch[end];			//1) front = front ^ end;		
										//
										//								 [from (1)]
			ch[end] ^= ch[i];			//2) end = end ^ front == end ^ (front ^ end) == front ^ (end ^ end)
										//    end = front ^ 0 | end = front			
										//								
										//							 [from (1)]							 [from (2)]
			ch[i] ^= ch[end];			//3) front = front ^ end == (front ^ end) ^ end == (front ^ end) ^ front
										//    front = end ^ (front ^ front) | front = end ^ 0 | front = end
			
			
			
		}
		strToRev = new String(ch);
		
		
		System.out.println("Reversed String: " + strToRev);	
		
	}
}
