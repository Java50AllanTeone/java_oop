package telran.people.test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.people.Company;
import telran.people.Employee;

class CompanyTest {
	static Company cmp;
	static Employee[] src;
	static Employee[] expId;
	static Employee[] expSlr;
	static Employee[] expAge;
	static Employee[] expName;
	static Employee[] expDep;
	
	@BeforeEach
	 void init() {
		src = new Employee[]{
				new Employee(3, 15_000, 1990, "Noah", "QA"),
				new Employee(2, 17_000, 1990, "Theo", "QA"),
				new Employee(5, 5_000, 1976, "Leo", "Project"),
				new Employee(6, 4_000, 1995, "Freddie", "Project"),
				new Employee(7, 1_000, 2000, "Alfie", "HR"),
				new Employee(33, 2_000, 2000, "Charlie", "HR"),
				new Employee(89, 40_000, 1992, "Harry", "Developer"),
				new Employee(38, 30_000, 1992, "Jack", "Developer")
		};
		cmp = new Company(src);
		
		expId = new Employee[]{
				new Employee(2, 17_000, 1990, "Theo", "QA"),
				new Employee(3, 15_000, 1990, "Noah", "QA"),
				new Employee(5, 5_000, 1976, "Leo", "Project"),
				new Employee(6, 4_000, 1995, "Freddie", "Project"),
				new Employee(7, 1_000, 2000, "Alfie", "HR"),
				new Employee(33, 2_000, 2000, "Charlie", "HR"),
				new Employee(38, 30_000, 1992, "Jack", "Developer"),
				new Employee(89, 40_000, 1992, "Harry", "Developer")
		};
		
		expSlr = new Employee[]{
				new Employee(7, 1_000, 2000, "Alfie", "HR"),
				new Employee(33, 2_000, 2000, "Charlie", "HR"),
				new Employee(6, 4_000, 1995, "Freddie", "Project"),
				new Employee(5, 5_000, 1976, "Leo", "Project"),
				new Employee(3, 15_000, 1990, "Noah", "QA"),
				new Employee(2, 17_000, 1990, "Theo", "QA"),
				new Employee(38, 30_000, 1992, "Jack", "Developer"),
				new Employee(89, 40_000, 1992, "Harry", "Developer")
		};
		
		expAge = new Employee[]{
				new Employee(5, 5_000, 1976, "Leo", "Project"),
				new Employee(2, 17_000, 1990, "Theo", "QA"),
				new Employee(3, 15_000, 1990, "Noah", "QA"),
				new Employee(38, 30_000, 1992, "Jack", "Developer"),
				new Employee(89, 40_000, 1992, "Harry", "Developer"),
				new Employee(6, 4_000, 1995, "Freddie", "Project"),
				new Employee(7, 1_000, 2000, "Alfie", "HR"),
				new Employee(33, 2_000, 2000, "Charlie", "HR")
		};
		
		expName = new Employee[]{
				new Employee(7, 1_000, 2000, "Alfie", "HR"),
				new Employee(33, 2_000, 2000, "Charlie", "HR"),
				new Employee(6, 4_000, 1995, "Freddie", "Project"),
				new Employee(89, 40_000, 1992, "Harry", "Developer"),
				new Employee(38, 30_000, 1992, "Jack", "Developer"),
				new Employee(5, 5_000, 1976, "Leo", "Project"),
				new Employee(3, 15_000, 1990, "Noah", "QA"),
				new Employee(2, 17_000, 1990, "Theo", "QA")	
		};

		expDep = new Employee[]{
				new Employee(38, 30_000, 1992, "Jack", "Developer"),
				new Employee(89, 40_000, 1992, "Harry", "Developer"),
				new Employee(7, 1_000, 2000, "Alfie", "HR"),
				new Employee(33, 2_000, 2000, "Charlie", "HR"),
				new Employee(5, 5_000, 1976, "Leo", "Project"),
				new Employee(6, 4_000, 1995, "Freddie", "Project"),
				new Employee(2, 17_000, 1990, "Theo", "QA"),
				new Employee(3, 15_000, 1990, "Noah", "QA")
		};
		
	}

	@Test
	void constructorTest() {
		assertArrayEquals(expId, cmp.employeesId);
		assertArrayEquals(expSlr, cmp.employeesSalary);
		assertArrayEquals(expAge, cmp.employeesAge);
		assertArrayEquals(expName, cmp.employeesName);
		assertArrayEquals(expDep, cmp.employeesDepartment);
	}
	
	@Test
	void getAllEmployeesTest() {
		assertArrayEquals(expId, cmp.getAllEmployees());
	}
	
	@Test
	void getEmployeesByAgeTest() {
		assertArrayEquals(expAge, cmp.getEmployeesByAge(0, 1000));
		assertArrayEquals(new Employee[]{}, cmp.getEmployeesByAge(0, 0));
		assertArrayEquals(new Employee[]{}, cmp.getEmployeesByAge(50, 100));
		
		expAge = new Employee[] { new Employee(5, 5_000, 1976, "Leo", "Project") };
		assertArrayEquals(expAge, cmp.getEmployeesByAge(47, 47));
		
		expAge = new Employee[] {
				new Employee(7, 1_000, 2000, "Alfie", "HR"),
				new Employee(33, 2_000, 2000, "Charlie", "HR") };
		assertArrayEquals(expAge, cmp.getEmployeesByAge(23, 23));
		
		expAge = new Employee[] {
				new Employee(38, 30_000, 1992, "Jack", "Developer"),
				new Employee(89, 40_000, 1992, "Harry", "Developer") };
		assertArrayEquals(expAge, cmp.getEmployeesByAge(30, 31));
		
		expAge = new Employee[] {
				new Employee(2, 17_000, 1990, "Theo", "QA"),
				new Employee(3, 15_000, 1990, "Noah", "QA"),
				new Employee(38, 30_000, 1992, "Jack", "Developer"),
				new Employee(89, 40_000, 1992, "Harry", "Developer") };
		assertArrayEquals(expAge, cmp.getEmployeesByAge(30, 35));
	}
	
	@Test
	void getEmployeesBySalaryTest() {
		assertArrayEquals(expSlr, cmp.getEmployeesBySalary(0, Integer.MAX_VALUE));
		assertArrayEquals(new Employee[]{}, cmp.getEmployeesBySalary(0, 0));
		assertArrayEquals(new Employee[]{}, cmp.getEmployeesBySalary(50_000, 100_000));
		
		expSlr = new Employee[] { new Employee(7, 1_000, 2000, "Alfie", "HR") };
		assertArrayEquals(expSlr, cmp.getEmployeesBySalary(0, 1000));
		
		expSlr = new Employee[] { 
				new Employee(38, 30_000, 1992, "Jack", "Developer"),
				new Employee(89, 40_000, 1992, "Harry", "Developer") };
		assertArrayEquals(expSlr, cmp.getEmployeesBySalary(25_000, 45_000));
		
		expSlr = new Employee[] { 
				new Employee(5, 5_000, 1976, "Leo", "Project"),
				new Employee(3, 15_000, 1990, "Noah", "QA") };
		assertArrayEquals(expSlr, cmp.getEmployeesBySalary(5_000, 15_000));
	}

	
	@Test
	void getEmployeesByDepartmentTest() {
		assertArrayEquals(new Employee[]{}, cmp.getEmployeesByDepartment("Some"));
		assertArrayEquals(new Employee[]{}, cmp.getEmployeesByDepartment(""));
		
		expDep = new Employee[] { 
				new Employee(5, 5_000, 1976, "Leo", "Project"),
				new Employee(6, 4_000, 1995, "Freddie", "Project") };
		assertArrayEquals(expDep, cmp.getEmployeesByDepartment("Project"));
	}
	
	@Test
	void getEmployeesByNameTest() {
		assertArrayEquals(new Employee[]{}, cmp.getEmployeesByName("Some"));
		assertArrayEquals(new Employee[]{}, cmp.getEmployeesByName(""));
		
		expName = new Employee[] { new Employee(6, 4_000, 1995, "Freddie", "Project") };
		assertArrayEquals(expName, cmp.getEmployeesByName("Freddie"));
	}


	@Test
	void getEmployeesByIdTest() {
		assertArrayEquals(expId, cmp.getEmployeesById(0, 1000));
		assertArrayEquals(new Employee[]{}, cmp.getEmployeesById(0, 0));
		assertArrayEquals(new Employee[]{}, cmp.getEmployeesById(300, 400));
		
		expId = new Employee[] { new Employee(2, 17_000, 1990, "Theo", "QA") };
		assertArrayEquals(expId, cmp.getEmployeesById(2, 2));
		
		expId = new Employee[] { 
				new Employee(33, 2_000, 2000, "Charlie", "HR"),
				new Employee(38, 30_000, 1992, "Jack", "Developer"),
				new Employee(89, 40_000, 1992, "Harry", "Developer") };
		assertArrayEquals(expId, cmp.getEmployeesById(33, 89));
	}


	@Test
	void getEmployeeTest() {
		assertNull(cmp.getEmployee(0));
		assertEquals(new Employee(6, 4_000, 1995, "Freddie", "Project"), cmp.getEmployee(6));
	}

	
	@Test
	void addEmployeeTest() {
		var empl1 = new Employee(555, 4_000_000, 2007, "ZZZZZZZZ", "ZZZZZZZZ");
		var empl2 = new Employee(0, 100, 1923, "AAAA", "AAAA");
		var empl3 = new Employee(8, 14_000, 1991, "Freddie", "Project");
		
		assertFalse(cmp.addEmployee(new Employee(6, 4_000, 1995, "Freddie", "Project")));
		assertFalse(cmp.addEmployee(new Employee(6, 44_000, 1997, "some", "some")));
		assertTrue(cmp.addEmployee(empl1));
		cmp.addEmployee(empl2);
		cmp.addEmployee(empl3);
		
		assertEquals(empl1, cmp.employeesId[cmp.employeesId.length - 1]);
		assertEquals(empl2, cmp.employeesId[0]);
		assertEquals(empl3, cmp.employeesId[6]);
		
		assertEquals(empl1, cmp.employeesSalary[cmp.employeesSalary.length - 1]);
		assertEquals(empl2, cmp.employeesSalary[0]);
		assertEquals(empl3, cmp.employeesSalary[5]);
		
		assertEquals(empl1, cmp.employeesAge[cmp.employeesAge.length - 1]);
		assertEquals(empl2, cmp.employeesAge[0]);
		assertEquals(empl3, cmp.employeesAge[4]);
		
		assertEquals(empl1, cmp.employeesName[cmp.employeesName.length - 1]);
		assertEquals(empl2, cmp.employeesName[0]);
		assertEquals(empl3, cmp.employeesName[4]);
		
		assertEquals(empl1, cmp.employeesDepartment[cmp.employeesDepartment.length - 1]);
		assertEquals(empl2, cmp.employeesDepartment[0]);
		assertEquals(empl3, cmp.employeesDepartment[7]);
	}
	
	@Test
	void removeEmployeesIfTest() {
		assertFalse(cmp.removeEmployeesIf(e -> e.getId() == 500));
		assertFalse(cmp.removeEmployeesIf(e -> e.getSalary() == 1_000_000));
		assertFalse(cmp.removeEmployeesIf(e -> e.getBirthYear() == 1977));
		assertFalse(cmp.removeEmployeesIf(e -> e.getName() == "Allan"));
		assertFalse(cmp.removeEmployeesIf(e -> e.getDepartment() == "DevOps"));
		
		assertTrue(cmp.removeEmployeesIf(e -> e.getId() == 2));
		expId = new Employee[]{
				new Employee(3, 15_000, 1990, "Noah", "QA"),
				new Employee(5, 5_000, 1976, "Leo", "Project"),
				new Employee(6, 4_000, 1995, "Freddie", "Project"),
				new Employee(7, 1_000, 2000, "Alfie", "HR"),
				new Employee(33, 2_000, 2000, "Charlie", "HR"),
				new Employee(38, 30_000, 1992, "Jack", "Developer"),
				new Employee(89, 40_000, 1992, "Harry", "Developer")
		};
		assertArrayEquals(expId, cmp.employeesId);
		
		cmp = new Company(src);
		assertTrue(cmp.removeEmployeesIf(e -> e.getSalary() == 15_000));
		expSlr = new Employee[]{
				new Employee(7, 1_000, 2000, "Alfie", "HR"),
				new Employee(33, 2_000, 2000, "Charlie", "HR"),
				new Employee(6, 4_000, 1995, "Freddie", "Project"),
				new Employee(5, 5_000, 1976, "Leo", "Project"),
				new Employee(2, 17_000, 1990, "Theo", "QA"),
				new Employee(38, 30_000, 1992, "Jack", "Developer"),
				new Employee(89, 40_000, 1992, "Harry", "Developer")
		};
		assertArrayEquals(expSlr, cmp.employeesSalary);
		
		cmp = new Company(src);
		assertTrue(cmp.removeEmployeesIf(e -> e.getBirthYear() == 1976));
		expAge = new Employee[]{
				new Employee(2, 17_000, 1990, "Theo", "QA"),
				new Employee(3, 15_000, 1990, "Noah", "QA"),
				new Employee(38, 30_000, 1992, "Jack", "Developer"),
				new Employee(89, 40_000, 1992, "Harry", "Developer"),
				new Employee(6, 4_000, 1995, "Freddie", "Project"),
				new Employee(7, 1_000, 2000, "Alfie", "HR"),
				new Employee(33, 2_000, 2000, "Charlie", "HR")
		};
		assertArrayEquals(expAge, cmp.employeesAge);
		
		cmp = new Company(src);
		assertTrue(cmp.removeEmployeesIf(e -> e.getName() == "Noah"));
		expName = new Employee[]{
				new Employee(7, 1_000, 2000, "Alfie", "HR"),
				new Employee(33, 2_000, 2000, "Charlie", "HR"),
				new Employee(6, 4_000, 1995, "Freddie", "Project"),
				new Employee(89, 40_000, 1992, "Harry", "Developer"),
				new Employee(38, 30_000, 1992, "Jack", "Developer"),
				new Employee(5, 5_000, 1976, "Leo", "Project"),
				new Employee(2, 17_000, 1990, "Theo", "QA")	
		};
		assertArrayEquals(expName, cmp.employeesName);
		
		cmp = new Company(src);
		assertTrue(cmp.removeEmployeesIf(e -> e.getDepartment() == "QA"));
		expDep = new Employee[]{
				new Employee(38, 30_000, 1992, "Jack", "Developer"),
				new Employee(89, 40_000, 1992, "Harry", "Developer"),
				new Employee(7, 1_000, 2000, "Alfie", "HR"),
				new Employee(33, 2_000, 2000, "Charlie", "HR"),
				new Employee(5, 5_000, 1976, "Leo", "Project"),
				new Employee(6, 4_000, 1995, "Freddie", "Project"),
		};
		assertArrayEquals(expDep, cmp.employeesDepartment);
	}
	
	@Test
	void removeEmployees() {
		assertFalse(cmp.removeEmployees(500));
		assertTrue(cmp.removeEmployees(2));
		
		
		expId = new Employee[]{
				new Employee(3, 15_000, 1990, "Noah", "QA"),
				new Employee(5, 5_000, 1976, "Leo", "Project"),
				new Employee(6, 4_000, 1995, "Freddie", "Project"),
				new Employee(7, 1_000, 2000, "Alfie", "HR"),
				new Employee(33, 2_000, 2000, "Charlie", "HR"),
				new Employee(38, 30_000, 1992, "Jack", "Developer"),
				new Employee(89, 40_000, 1992, "Harry", "Developer")
		};
		assertArrayEquals(expId, cmp.employeesId);
		
		expSlr = new Employee[]{
				new Employee(7, 1_000, 2000, "Alfie", "HR"),
				new Employee(33, 2_000, 2000, "Charlie", "HR"),
				new Employee(6, 4_000, 1995, "Freddie", "Project"),
				new Employee(5, 5_000, 1976, "Leo", "Project"),
				new Employee(3, 15_000, 1990, "Noah", "QA"),
				new Employee(38, 30_000, 1992, "Jack", "Developer"),
				new Employee(89, 40_000, 1992, "Harry", "Developer")
		};
		assertArrayEquals(expSlr, cmp.employeesSalary);
		
		expAge = new Employee[]{
				new Employee(5, 5_000, 1976, "Leo", "Project"),
				new Employee(3, 15_000, 1990, "Noah", "QA"),
				new Employee(38, 30_000, 1992, "Jack", "Developer"),
				new Employee(89, 40_000, 1992, "Harry", "Developer"),
				new Employee(6, 4_000, 1995, "Freddie", "Project"),
				new Employee(7, 1_000, 2000, "Alfie", "HR"),
				new Employee(33, 2_000, 2000, "Charlie", "HR")
		};
		assertArrayEquals(expAge, cmp.employeesAge);
		
		expName = new Employee[]{
				new Employee(7, 1_000, 2000, "Alfie", "HR"),
				new Employee(33, 2_000, 2000, "Charlie", "HR"),
				new Employee(6, 4_000, 1995, "Freddie", "Project"),
				new Employee(89, 40_000, 1992, "Harry", "Developer"),
				new Employee(38, 30_000, 1992, "Jack", "Developer"),
				new Employee(5, 5_000, 1976, "Leo", "Project"),
				new Employee(3, 15_000, 1990, "Noah", "QA"),
		};
		assertArrayEquals(expName, cmp.employeesName);

		expDep = new Employee[]{
				new Employee(38, 30_000, 1992, "Jack", "Developer"),
				new Employee(89, 40_000, 1992, "Harry", "Developer"),
				new Employee(7, 1_000, 2000, "Alfie", "HR"),
				new Employee(33, 2_000, 2000, "Charlie", "HR"),
				new Employee(5, 5_000, 1976, "Leo", "Project"),
				new Employee(6, 4_000, 1995, "Freddie", "Project"),
				new Employee(3, 15_000, 1990, "Noah", "QA")
		};
		assertArrayEquals(expDep, cmp.employeesDepartment);
	}
	
	
	
	

}
