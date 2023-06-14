package telran.people.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.people.Company;
import telran.people.Employee;

class CompanyTest {
	Company company;
	Employee[] employees = new Employee[] {
			new Employee(20, 30_000, 1970, "Ned", "Hand"),
			new Employee(40, 50_000, 1965, "Robert", "King"),
			new Employee(1, 10_000, 1950, "Barristan", "Knight"),
			new Employee(15, 2_000, 1990, "John", "Watcher")	
	};	

	@BeforeEach
	void setUp() throws Exception {
		company = new Company(employees);
	}

	@Test
	void getAllEmployeesTest() {
		Employee[] sortedId = new Employee[] {
				new Employee(1, 10_000, 1950, "Barristan", "Knight"),
				new Employee(15, 2_000, 1990, "John", "Watcher"),
				new Employee(20, 30_000, 1970, "Ned", "Hand"),
				new Employee(40, 50_000, 1965, "Robert", "King")	
		};
		assertArrayEquals(sortedId, company.getAllEmployees(new EmployeeComparator("id")));
		
		Employee[] sortedAge = new Employee[] {
				new Employee(15, 2_000, 1990, "John", "Watcher"),
				new Employee(20, 30_000, 1970, "Ned", "Hand"),
				new Employee(40, 50_000, 1965, "Robert", "King"),
				new Employee(1, 10_000, 1950, "Barristan", "Knight")
		};
		assertArrayEquals(sortedAge, company.getAllEmployees(new EmployeeComparator("age")));
		
		Employee[] sortedSalary = new Employee[] {
				new Employee(15, 2_000, 1990, "John", "Watcher"),
				new Employee(1, 10_000, 1950, "Barristan", "Knight"),
				new Employee(20, 30_000, 1970, "Ned", "Hand"),
				new Employee(40, 50_000, 1965, "Robert", "King")
		};
		assertArrayEquals(sortedSalary, company.getAllEmployees(new EmployeeComparator("salary")));
	}
	

	@Test
	void getAllEmployeesByAgeTest() {
		Employee[] expected1 = new Employee[] {
				new Employee(15, 2_000, 1990, "John", "Watcher"),
				new Employee(20, 30_000, 1970, "Ned", "Hand"),
				new Employee(40, 50_000, 1965, "Robert", "King"),
				new Employee(1, 10_000, 1950, "Barristan", "Knight")	
		};
		assertArrayEquals(expected1, company.getAllEmployeesByAge(1500, 2000));
		
		Employee[] expected2 = new Employee[] {};
		assertArrayEquals(expected2, company.getAllEmployeesByAge(1991, 2000));
		
		
		Employee[] expected3 = new Employee[] {
				new Employee(20, 30_000, 1970, "Ned", "Hand"),
				new Employee(1, 10_000, 1950, "Barristan", "Knight")		
		};
		assertArrayEquals(expected3, company.getAllEmployeesByAge(1950, 1970));
	}

}
