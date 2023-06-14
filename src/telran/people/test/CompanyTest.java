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
	
	@Test
	void getEmployeesBySalaryTest() {
		Employee[] expected1 = new Employee[] {
				new Employee(15, 2_000, 1990, "John", "Watcher"),
				new Employee(1, 10_000, 1950, "Barristan", "Knight"),
				new Employee(20, 30_000, 1970, "Ned", "Hand"),
				new Employee(40, 50_000, 1965, "Robert", "King")	
		};
		assertArrayEquals(expected1, company.getEmployeesBySalary(1, 100_000));
		
		Employee[] expected2 = new Employee[] {};
		assertArrayEquals(expected2, company.getEmployeesBySalary(1, 100));
		
		Employee[] expected3 = new Employee[] {
				new Employee(1, 10_000, 1950, "Barristan", "Knight"),
				new Employee(20, 30_000, 1970, "Ned", "Hand")
		};
		assertArrayEquals(expected3, company.getEmployeesBySalary(10_000, 30_000));
	}
	
	@Test
	void getEmployeesByDepartmentTest() {
		Employee[] expected1 = new Employee[] {
				new Employee(1, 10_000, 1950, "Barristan", "Knight"),	
		};
		assertArrayEquals(expected1, company.getEmployeesByDepartment("Knight"));
		
		Employee[] expected2 = new Employee[] {};
		assertArrayEquals(expected2, company.getEmployeesByDepartment(""));
		
		
		company = new Company(new Employee[]{
				new Employee(15, 2_000, 1990, "John", "Watcher"),
				new Employee(1, 10_000, 1950, "Barristan", "Knight"),
				new Employee(20, 30_000, 1970, "Ned", "Knight"),
				new Employee(40, 50_000, 1965, "Robert", "King")	
		});
		Employee[] expected3 = new Employee[] {
				new Employee(1, 10_000, 1950, "Barristan", "Knight"),
				new Employee(20, 30_000, 1970, "Ned", "Knight"),	
		};
		assertArrayEquals(expected3, company.getEmployeesByDepartment("Knight"));
	}
	
	@Test
	void addEmployeeTest() {
		assertFalse(company.addEmployee(new Employee(1, 10_000, 1950, "Barristan", "Knight")));
		assertTrue(company.addEmployee(new Employee(23, 30_000, 1960, "Sandor", "Knight")));
		
		Employee[] expected = {
			new Employee(1, 10_000, 1950, "Barristan", "Knight"),
			new Employee(15, 2_000, 1990, "John", "Watcher"),
			new Employee(20, 30_000, 1970, "Ned", "Hand"),
			new Employee(23, 30_000, 1960, "Sandor", "Knight"),
			new Employee(40, 50_000, 1965, "Robert", "King"),
		};
		assertArrayEquals(expected, company.getAllEmployees(new EmployeeComparator("id")));
	}

}
