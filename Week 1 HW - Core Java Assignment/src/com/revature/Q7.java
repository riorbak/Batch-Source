package com.revature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//Q7. Sort two employees based on their name, department, 
// and age using the Comparator interface. 
public class Q7
{	
	ArrayList<Employee> empList = new ArrayList<Employee>();		//set up employee ArrayList
	
		
	void populateEmpList()
	{		
		//pre-made employees
		Employee e1 = new Employee("Tom", "Maintenance", 40);
		Employee e2 = new Employee("Seth", "Security", 25);
		
		//temp code for pre-made employees
		empList.add(e1);
		empList.add(e2);
	}
	
	void sortByComparators()
	{
		populateEmpList();										//Create 2 Employees and populate ArrayList
		
		System.out.println("\nUnsorted: ");						//output original sorting
        for (int i = 0; i < empList.size(); i++)
            System.out.println(empList.get(i));
        
        Collections.sort(empList, new sortByName());     		//comparator sort by name
        
        System.out.println("\nSorted By Name: ");				//output results
        for (int i = 0; i < empList.size(); i++)
            System.out.println(empList.get(i));
        	
        Collections.sort(empList, new sortByDept());			//comparator sort by department
        
        System.out.println("\nSorted by Department: ");			//output results
        for (int i = 0; i < empList.size(); i++)
            System.out.println(empList.get(i));
        
        Collections.sort(empList, new sortByAge());				//comparator sort by age
        
        System.out.println("\nSorted by Age: ");				//output results
        for (int i = 0; i < empList.size(); i++)
            System.out.println(empList.get(i));
	}
	
}	

class sortByName implements Comparator<Employee>
{
	@Override
	public int compare(Employee o1, Employee o2) {
		
		return o1.getName().compareTo(o2.getName());
	}	
}
class sortByDept implements Comparator<Employee>
{
	@Override
	public int compare(Employee o1, Employee o2) {
		
		return o1.getDepartment().compareTo(o2.getDepartment());
	}	
}
class sortByAge implements Comparator<Employee>
{
	@Override
	public int compare(Employee o1, Employee o2) {
		
		return o1.getAge() - o2.getAge();
	}	
}


