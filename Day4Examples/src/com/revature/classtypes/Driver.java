package com.revature.classtypes;

import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {
		String[] fishTypes = {"One","two","red","blue"};
		Guppie bob = new Guppie("Bob","Clown");
		
		ArrayList<Fishes> hotdog = new ArrayList<Fishes>();
		Clown jay = new Clown("Jay","Guppie");
		
		bob.eat();
		bob.swim(9001);
		bob.typeOfFFFFFFFFFFFFFIIIIIIIIIIIIIIIIIISSSSSSSSSSSSSSSSSSSHHHHHHHHHHHHHHHHHHH(fishTypes);
		System.out.println(bob.getName());
		
		fishToHotdog(bob, hotdog);
		fishToHotdog(jay, hotdog);
	}

	public static void fishToHotdog(Fishes g, ArrayList<Fishes> a) {
		a.add(g);
		System.out.println("Added "+g.getName());
	}
}
