package com.revature;

public class Employee 
{
	private String name;
	private String department;
	private int age;
	
	

	public Employee() //default constructor
	{
		this.name = "John Doe";
		this.department = "Not Assigned";
		this.age = 0;
	}
	
	
	public Employee(String name, String department, int age)
	{
		this.name = name;
		this.department = department;
		this.age = age;
	}
	
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getDepartment() 
	{
		return department;
	}
	
	public void setDepartment(String department) 
	{
		this.department = department;
	}
	
	public int getAge() 
	{
		return age;
	}
	
	public void setAge(int age) 
	{
		this.age = age;
	}
	
	@Override
	public String toString()
	{
		return "Employee { " + this.name + ", " + this.age + ", " + this.department + " }";
	}
	
}
