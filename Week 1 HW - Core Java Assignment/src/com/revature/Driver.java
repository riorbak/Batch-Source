package com.revature;

import java.util.Scanner;

//runs all the Questions for the Homework
public class Driver {
	
	//establish input scanner to be used throughout the whole package.
	public static final Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args)
	{	
		//Q1
		System.out.println("Question 01:");
		Q1 bub = new Q1();
		
		bub.bubbleSort();
		
		//Q2
		
		System.out.println("\nQuestion 02:");
		Q2 fib = new Q2();
		
		fib.findFibonacci();
		
		//Q3
		System.out.println("\nQuestion 03:");
		Q3 revStr = new Q3();
		
		revStr.revString();		
		
		//Q4
		System.out.println("\nQuestion 04:");
		Q4 fct = new Q4();
		
		fct.factorial();
		
		//Q5
		System.out.println("\nQuestion 05:");
		Q5 ss = new Q5();
		
		ss.workQ5();
		
		//Q6
		System.out.println("\nQuestion 06: ");
		Q6 m = new Q6();
		
		m.determineEvenNum();
		
		//Q7
		System.out.println("\nQuestion 07: ");
		Q7 emp = new Q7();
		
		emp.sortByComparators();
		
		//Q8
		System.out.println("\nQuestion 08:");
		Q8 pal = new Q8();

		pal.checkForPalindromes();
		
		//Q9
		System.out.println("\nQuestion 09:");
		Q9 pri = new Q9();
		
		pri.findPrimeNum();
		
		//Q10
		System.out.println("\nQuestion 10: ");
		Q10 ter = new Q10();
		
		ter.findMinVal();
		
		//Q11
		System.out.println("\nQuestion 11: ");
		Q11 flo = new Q11();
		
		flo.runQ11();
		
		//Q12
		System.out.println("\nQuestion 12: ");
		Q12 evn = new Q12();
		
		evn.findEvenNumbers();
		
		//Q13
		System.out.println("\nQuestion 13: ");
		Q13 tri = new Q13();
		
		tri.drawTriangle();
		
		//Q14
		System.out.println("\nQuestion 14: ");
		Q14 muCh = new Q14();
		
		muCh.menuChoice();
			
		//Q15
		System.out.println("\nQuestion 15: ");
		Q15 ma = new Q15();
		
		ma.runQ15();
		
		//Q16
		System.out.println("\nQuestion 16: ");
		Q16 cmdLine = new Q16();
		
		Q16.main(args);
				
		//Q17
		System.out.println("\nQuestion 17: ");
		Q17 intrest = new Q17();
		
		intrest.calcIntrest();
		
		//Q18
		System.out.println("\nQuestion 18: ");
		Q18 abst = new Q18();
		
		abst.runQ18();
	
		//Q19
		System.out.println("\nQuestion 19: ");
		Q19 arLi = new Q19();
		
		arLi.runQ19();
		
		//Q20
		System.out.println("\nQuestion 20: ");
		Q20 inFileTest = new Q20();
		
		inFileTest.displayDataFile(); 				
		
		sc.nextLine();
		
		sc.close();		//close the scanner
	}
}
