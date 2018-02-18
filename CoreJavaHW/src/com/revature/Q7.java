package com.revature;

import java.util.Comparator;

public class Q7 implements Comparator<Employee> {
	public void run() {
		Employee e1 = new Employee("Joe", "Sales", 25);		//creating some base employees
		Employee e2 = new Employee("Beth", "Sales", 25);
		Employee e3 = new Employee("Joe", "IT", 25);
		Employee e4 = new Employee("Joe", "Sales", 24);
		
		Employee[] empArray = {e1,e2,e3,e4};	//to go through later easier
		
		System.out.println("Sorting by name, then department (both alphabetically), then age (younger is first).");
		System.out.println("First example - Different name: ");
		
		for(int i=0; i<empArray.length-1; i++) {
			if(compare(empArray[0], empArray[i+1])<0) {	//comparing the first employee to the other three
				//if .compare, overridden from the comparator interface, returns that the first one is before the one being checked 
				System.out.println(empArray[0].toString() + " goes before " + empArray[i+1].toString());	//print the first one, then the second
			}
			else {	//if they're equal or the one being checked goes before the first employee
				System.out.println(empArray[i+1].toString() + " goes before " + empArray[0].toString());	//say so
			}
			if(i==0) {	//not wanting to replicate the if statements above but wanting labels for each example
				System.out.println("Second example - Different department: ");
			}
			if(i==1) {
				System.out.println("Third example - Different age: ");
			}
		}
	}

	@Override
	public int compare(Employee employee1, Employee employee2) {
		//from comparator documentation through eclipse:
		//compare(Object o1, Object o2) returns: a negative integer, zero, or a positive integer 
		//as the first argument is less than, equal to, or greater than the second. 
		if(employee1.getName().compareTo(employee2.getName()) < 0) {	
			//String.compareTo(String) returns negative if first is before second alphabetically
			//so... if employee1's name is alphabetically before the second...
			return -1;	//less than, goes first
		}
		else if(employee1.getName().compareTo(employee2.getName()) > 0) {	
			//if employee1's name is after 2's in the alphabet
			return 1;	//greater than, goes after
		}
		else {	//if employee names are the same, check the departments
			if(employee1.getDepartment().compareTo(employee2.getDepartment()) < 0) {
				return -1;	//goes first
			}
			else if(employee1.getDepartment().compareTo(employee2.getDepartment()) > 0) {
				return 1;	//goes second
			}
			else {	//if names AND departments are same
				if(employee1.getAge() < employee2.getAge()) {
					return -1; //younger, less than, goes first
				}
				else if(employee1.getAge() > employee2.getAge()) {
					return 1;	//older, goes second
				}
			}
		}
		return 0;	//if everything's all the same
	}
}

class Employee{
	private String name;
	private String department;
	private int age;
	
	//everything below is just default stuff from Ctrl+Alt+S
	@Override
	public String toString() {
		return "[" + name + ", department=" + department + ", age=" + age + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Employee(String name, String department, int age) {
		super();
		this.name = name;
		this.department = department;
		this.age = age;
	}
}