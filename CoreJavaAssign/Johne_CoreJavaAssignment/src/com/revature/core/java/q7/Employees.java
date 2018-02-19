package com.revature.core.java.q7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 
 * @author Johne Vang
 * Q7. Sort two employees based on their name, department, and age using the Comparator interface.
 * 
 */
public class Employees {

	private String name, dept;
	private int age;
	
	public Employees() {}
	
	public Employees(String name, String dept, int age) {
		this.name = name;
		this.dept = dept;
		this.age = age;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the dept
	 */
	public String getDept() {
		return dept;
	}



	/**
	 * @param dept the dept to set
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}



	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}



	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Employee name: " + name + "\nEmployee's dept: " + dept + "\n"
				+ "Employee's age: " + age + "\n";
	}
	
}	//end of class
