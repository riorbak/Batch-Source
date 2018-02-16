package com.revature;

import java.util.ArrayList;

import com.revature.beans.Guitar;
import com.revature.io.SerializeDemo;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<Guitar> guitarList = new ArrayList();
		
		Guitar gibson= new Guitar(1,"Les Paul", "Red",1500.35);
		Guitar lucille = new Guitar(2, "es-335", "Black", 14999.99);
		
		guitarList.add(gibson);
		guitarList.add(lucille);
		
		
		//System.out.println(gibson.toString());
		//SerializeDemo.writeObject(gibson, "out.txt");
		
		SerializeDemo.writeObject(guitarList, "out.txt");
		
		//Guitar gib=(Guitar) SerializeDemo.readObject("out.txt");
		//System.out.println(gib.toString());
		
		ArrayList<Guitar> git = (ArrayList<Guitar>)SerializeDemo.readObject("out.txt");
		System.out.println(git.get(1).getCost());
		
	}

}
