package telran.people;

import java.time.Year;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class Company {	
	//public for tests;
	public Employee[] employeesId;
	public Employee[] employeesSalary;
	public Employee[] employeesAge;
	public Employee[] employeesName;
	public Employee[] employeesDepartment;
	
	private Comparator<Employee> cmpAge = Comparator.comparingInt(Employee::getBirthYear);
	private Comparator<Employee> cmpSlr = Comparator.comparingInt(Employee::getSalary);
	private Comparator<Employee> cmpDep = Comparator.comparing(Employee::getDepartment);
	private Comparator<Employee> cmpName = Comparator.comparing(Employee::getName);
	private Comparator<Employee> cmpId = Comparator.naturalOrder();

	public Company(Employee[] employees) {
		this.employeesId = Arrays.copyOf(employees, employees.length);
		this.employeesSalary = Arrays.copyOf(employees, employees.length);
		this.employeesAge = Arrays.copyOf(employees, employees.length);
		this.employeesName = Arrays.copyOf(employees, employees.length);
		this.employeesDepartment = Arrays.copyOf(employees, employees.length);
		
		Arrays.sort(employeesId, cmpId);
		Arrays.sort(employeesSalary, cmpSlr.thenComparing(cmpId));
		Arrays.sort(employeesAge, cmpAge.thenComparing(cmpId));
		Arrays.sort(employeesName, cmpName.thenComparing(cmpId));
		Arrays.sort(employeesDepartment, cmpDep.thenComparing(cmpId));
	}
	
	public Employee[] getAllEmployees() {
		return Arrays.copyOf(employeesId, employeesId.length);
	}
	
	public Employee[] getEmployeesByAge(int ageFrom, int ageTo) {
		int curYear = Year.now().getValue();

		Employee from = new Employee(-1, 0, curYear - ageTo);
		Employee to = new Employee(-1, 0, curYear - ageFrom);
		return getFilteredArr(employeesAge, from, to, cmpAge);
	}
	
	public Employee[] getEmployeesBySalary(int salaryFrom, int salaryTo) {
		Employee from = new Employee(-1, salaryFrom);
		Employee to = new Employee(-1, salaryTo);
		
		return getFilteredArr(employeesSalary, from, to, cmpSlr);
	}
	
	public Employee[] getEmployeesByDepartment(String department) {
		Employee from = new Employee("", department);
		Employee to = new Employee("", department);
		return getFilteredArr(employeesDepartment, from, to, cmpDep);
	}
	
	public Employee[] getEmployeesByName(String name) {
		Employee from = new Employee(name);
		Employee to = new Employee(name);
		return getFilteredArr(employeesName, from, to, cmpName);
	}
	
	public Employee[] getEmployeesById(int idFrom, int idTo) {
		Employee from = new Employee(idFrom);
		Employee to = new Employee(idTo);
		return getFilteredArr(employeesId, from, to, cmpId);
	}
	
	public Employee getEmployee(int id) {
		var res = getEmployeesById(id, id);
		return res.length == 0 ? null : res[0];
	}
	
	public boolean addEmployee(Employee empl) {
		boolean res = false;

		var indId = Arrays.binarySearch(employeesId, empl, cmpId);
		var indSalary = Arrays.binarySearch(employeesSalary, empl, cmpSlr.thenComparing(cmpId));
		var indAge = Arrays.binarySearch(employeesAge, empl, cmpAge.thenComparing(cmpId));
		var indName = Arrays.binarySearch(employeesName, empl, cmpName.thenComparing(cmpId));
		var indDepartment = Arrays.binarySearch(employeesDepartment, empl, cmpDep.thenComparing(cmpId));
		
		if (indId < 0) {
			employeesId = addToArr(employeesId, getValidIndex(indId), empl);
			employeesSalary = addToArr(employeesSalary, getValidIndex(indSalary), empl);
			employeesAge = addToArr(employeesAge, getValidIndex(indAge), empl);
			employeesName = addToArr(employeesName, getValidIndex(indName), empl);
			employeesDepartment = addToArr(employeesDepartment, getValidIndex(indDepartment), empl);
			
			res = true;
		}
		return res;
	}

	public boolean removeEmployeesIf(Predicate<Employee> predicate) {
		predicate = predicate.negate();
		int prevSize = employeesId.length;
		
		employeesId = removeFromArr(employeesId, predicate);
		employeesSalary = removeFromArr(employeesSalary, predicate);
		employeesAge = removeFromArr(employeesAge, predicate);
		employeesName = removeFromArr(employeesName, predicate);
		employeesDepartment = removeFromArr(employeesDepartment, predicate);
	
		return prevSize > employeesId.length;
	}
	
	public boolean removeEmployees(int id) {
		try {
			var employee = getEmployee(id);
			
			var indId = Arrays.binarySearch(employeesId, employee, cmpId);
			var indSalary = Arrays.binarySearch(employeesSalary, employee, cmpSlr.thenComparing(cmpId));
			var indAge = Arrays.binarySearch(employeesAge, employee, cmpAge.thenComparing(cmpId));
			var indName = Arrays.binarySearch(employeesName, employee, cmpName.thenComparing(cmpId));
			var indDepartment = Arrays.binarySearch(employeesDepartment, employee, cmpDep.thenComparing(cmpId));
			
			employeesId = removeFromArr(employeesId, indId);
			employeesSalary = removeFromArr(employeesSalary, indSalary);
			employeesAge = removeFromArr(employeesAge, indAge);
			employeesName = removeFromArr(employeesName, indName);
			employeesDepartment = removeFromArr(employeesDepartment, indDepartment);
		} catch (NullPointerException e) {
			return false;
		}	
		return true;
	}
	
	
	
	private Employee[] addToArr(Employee[] arr, int ind, Employee empl) {
		Employee[] res = new Employee[arr.length + 1];
		System.arraycopy(arr, 0, res, 0, ind);
		System.arraycopy(arr, ind, res, ind + 1, arr.length - ind);
		res[ind] = empl;
		
		return res;
	}
	
	private Employee[] removeFromArr(Employee[] src, Predicate<Employee> predicate) {
		Employee[] res = new Employee[src.length];
		int index = 0;
		
		for (int i = 0; i < src.length; i++) {
			if (predicate.test(src[i])) {
				res[index++] = src[i];
			}
		}
		return Arrays.copyOf(res, index);
	}
	
	private Employee[] removeFromArr(Employee[] src, int i) {
		Employee[] res = new Employee[src.length - 1];
		System.arraycopy(src, 0, res, 0, i);
		System.arraycopy(src, i + 1, res, i, res.length - i);
		return res;
	}
	
	private Employee[] getFilteredArr(Employee[] src, Employee from, Employee to, Comparator<Employee> comp) {
		int start = Arrays.binarySearch(src, from, comp.thenComparing(cmpId));
		int end = Arrays.binarySearch(src, to, comp.thenComparing(Comparator.reverseOrder()));
		
		try {
			return Arrays.copyOfRange(src, getValidIndex(start), end >= 0 ? ++end : getValidIndex(end));
		} catch (Exception e) {
			return new Employee[] {};
		}
	}
	
	private int getValidIndex(int index) {
		return index < 0 ? Math.abs(index) - 1 : index;
	}
	

	
	
}