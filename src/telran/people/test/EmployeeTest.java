package telran.people.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import telran.people.Employee;

class EmployeeTest {
	static Employee[] employees;
	static Employee[] sorted;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		employees = new Employee[] {
				new Employee(20, 30_000, 1970, "Ned", "Hand"),
				new Employee(40, 50_000, 1965, "Robert", "King"),
				new Employee(1, 10_000, 1950, "Barristan", "Knight"),
				new Employee(15, 2_000, 1990, "John", "Watcher")	
		};	
		
		sorted = new Employee[] {
				new Employee(1, 10_000, 1950, "Barristan", "Knight"),
				new Employee(15, 2_000, 1990, "John", "Watcher"),
				new Employee(20, 30_000, 1970, "Ned", "Hand"),
				new Employee(40, 50_000, 1965, "Robert", "King")	
		};
	}

	@Test
	void test() {
		Arrays.sort(employees);
		assertArrayEquals(sorted, employees);
	}

}
