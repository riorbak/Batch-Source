package com.revature;

import java.util.ArrayList;

//Q8. Write a program that stores the following strings in an ArrayList and saves all the palindromes in another ArrayList.
//“karan”, “madam”, ”tom”, “civic”, “radar”, “jimmy”, “kayak”, “john”,  “refer”, “billy”, “did”
public class Q8 
{
	ArrayList<String> wordList = new ArrayList<String>(); 
	static String[] words = {"karan", "madam", "tom", "civic", "radar", "jimmy", "kayak", "john",  "refer", "billy", "did"};
	
	
	void populateArrayList()
	{		
		for(int i = 0; i < words.length; i++)
		{
			wordList.add(words[i]);
		}
	}
	
	void checkForPalindromes()
	{
		populateArrayList();
		
		ArrayList<String> palindrones = new ArrayList<String>();
		
		for(String w : wordList)
		{
			int end = w.length()-1;						//end of string		
			boolean isPal = true;
			
			for(int i = 0; i  < end; i++, end--)
			{
				if(w.charAt(i) != w.charAt(end))
					isPal = false;			
			}
			
			if(isPal)
				palindrones.add(w);			
		}
		
		printArrayLists(palindrones);		
	}
	
	void printArrayLists(ArrayList<String> p)
	{
		System.out.println("Original List: ");
		for(String w : wordList)
			System.out.print(w + "  ");
		
		System.out.println("\n Palindrone List: ");
		for(String w : p)
			System.out.print(w + "  ");
	}
	
	
}
