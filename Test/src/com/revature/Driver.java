package com.revature;

import java.util.ArrayList;

import com.revature.beans.Guitar;
import com.revature.io.SerializeDemo;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Guitar> guitarList = new ArrayList<Guitar>();

		Guitar gibson= new Guitar(1,"Les Paul", "Red",1500.35);
		Guitar lucille= new Guitar(1,"es-335", "black",187453500.35);
		
		guitarList.add(gibson);
		guitarList.add(lucille);
		//System.out.println(gibson.toString());
		//SerializeDemo.writeObject(guitarList, "out.txt");		//uncomment this to write into the file
		//SerializeDemo.writeObject(lucille, "out.txt");
		ArrayList<Guitar> git=(ArrayList<Guitar>) SerializeDemo.readObject("out.txt");	//uncomment this to read the object from the file
		System.out.println(git.get(0).getCost());
		System.out.println(git.get(1).getCost());
	}

}