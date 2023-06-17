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
		
	
		

	}

}
