package telran.people.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.people.Company;
import telran.people.Employee;

class AddTest {

	@Test
	void test() {
		Company cmp = new Company(new Employee[] {
				new Employee(10, 10_000, 1990, "Joe", "QA"),
				new Employee(5, 5_000, 1976, "Carl", "Manager"),
				new Employee(30, 30_000, 1995, "Yossi", "Project"),
				new Employee(1, 1_000, 2000, "Shira", "HR"),
				new Employee(100, 100_000, 1992, "Xi", "Prog")
		});
		/*
		System.out.println("Init");
		System.out.println(Arrays.toString(cmp.employeesId));
		System.out.println(Arrays.toString(cmp.employeesSalary));
		System.out.println(Arrays.toString(cmp.employeesAge));
		System.out.println(Arrays.toString(cmp.employeesName));
		System.out.println(Arrays.toString(cmp.employeesDepartment));*/
		
		cmp.addEmployee(new Employee(22, 22_000, 1988, "Allan", "Developer"));
		cmp.addEmployee(new Employee(22, 22_000, 1988, "Allan1", "Developer"));
		cmp.addEmployee(new Employee(23, 22_000, 1988, "Allan1", "Developer"));
		cmp.addEmployee(new Employee(0, 22_000, 1988, "Allan2", "Developer"));
		cmp.addEmployee(new Employee(23, 22_000, 1988, "Allan1", "Developer"));
		
		System.out.println("After adding");
		//System.out.println(Arrays.toString(cmp.employeesId));
		//System.out.println(Arrays.toString(cmp.employeesSalary));
		System.out.println(Arrays.toString(cmp.employeesAge));

		System.out.println(Arrays.toString(cmp.getAllEmployeesByAge(1990, 2000)));
		
	
		

	}

}
