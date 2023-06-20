package telran.people.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.people.Company;
import telran.people.Employee;

class CompanyTest {

	@Test
	void test() {
		Company cmp = new Company(new Employee[] {
				new Employee(10, 10_000, 1990, "Joe", "QA"),
				new Employee(11, 10_000, 1990, "Joe", "QA"),
				new Employee(12, 10_000, 1990, "Joe", "QA"),
				new Employee(13, 10_000, 1990, "Joe", "QA"),
				new Employee(5, 5_000, 1976, "Carl", "Manager"),
				new Employee(30, 30_000, 1995, "Yossi", "Project"),
				new Employee(31, 30_000, 1995, "Yossi", "Project"),
				new Employee(32, 30_000, 1995, "Yossi", "Project"),
				new Employee(1, 1_000, 2000, "Shira", "HR"),
				new Employee(2, 1_000, 2000, "Shira", "HR"),
				new Employee(3, 1_000, 2000, "Shira", "HR"),
				new Employee(4, 1_000, 2000, "Shira", "HR"),
				new Employee(100, 100_000, 1992, "Xi", "Prog")
		});
		
		System.out.println(Arrays.toString(cmp.getAllEmployees()));
		System.out.println(Arrays.toString(cmp.getEmployeesByAge(20, 35)));
		System.out.println(Arrays.toString(cmp.getEmployeesBySalary(10000, 30000)));
		System.out.println(Arrays.toString(cmp.getEmployeesByDepartment("QA")));
		System.out.println(Arrays.toString(cmp.getEmployeesByName("Shira")));
		
		cmp.addEmployee(new Employee(9, 10000, 1991, "Ku", "QA"));
		System.out.println(Arrays.toString(cmp.getEmployeesBySalary(10000, 10000)));
//		System.out.println(cmp.find());
		//cmp.find();
		
	
		

	}

}
