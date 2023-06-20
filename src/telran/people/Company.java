package telran.people;

import java.time.Year;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class Company {	
	public Employee[] employeesId;
	public Employee[] employeesSalary;
	public Employee[] employeesAge;
	public Employee[] employeesName;
	public Employee[] employeesDepartment;
	
	Comparator<Employee> cmpAge = (e1, e2) -> e1.getBirthYear() - e2.getBirthYear();
	Comparator<Employee> cmpSlr = (e1, e2) -> e1.getSalary() - e2.getSalary();
	Comparator<Employee> cmpDep = (e1, e2) -> e1.getDepartment().compareTo(e2.getDepartment());
	Comparator<Employee> cmpName = (e1, e2) -> e1.getName().compareTo(e2.getName());
	Comparator<Employee> cmpId = Comparator.naturalOrder();

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
	
	
	public int find() {
		System.out.println(Arrays.toString(getFilteredArr(employeesSalary, new Employee(-1, 100500), new Employee(-1, 100500), cmpSlr)));
		
		return Arrays.binarySearch(employeesSalary, new Employee(500, 1000) , cmpSlr.thenComparing(cmpId));
	}
	
	public Employee[] getAllEmployees() {
		return Arrays.copyOf(employeesId, employeesId.length);
	}
	
	public Employee[] getEmployeesByAge(int ageFrom, int ageTo) {
		int curYear = Year.now().getValue();
		int yearFrom = curYear - ageTo;
		int yearTo = curYear - ageFrom;
		
		Employee from = new Employee(0, 0, yearFrom);
		Employee to = new Employee(0, 0, yearTo);
		return getFilteredArr(employeesAge, from, to, cmpAge);	
//		Comparator<Employee> comp = Comparator.comparing(Employee::getBirthYear);
	}
	
	public Employee[] getEmployeesBySalary(int salaryFrom, int salaryTo) {
		Employee from = new Employee(0, salaryFrom);
		Employee to = new Employee(0, salaryTo, 0);
		
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

			indId = getValidIndex(indId);
			indSalary = getValidIndex(indSalary);
			indAge = getValidIndex(indAge);
			indName = getValidIndex(indName);
			indDepartment = getValidIndex(indDepartment);
			
			employeesId = addToArr(employeesId, indId, empl);
			employeesSalary = addToArr(employeesSalary, indSalary, empl);
			employeesAge = addToArr(employeesAge, indAge, empl);
			employeesName = addToArr(employeesName, indName, empl);
			employeesDepartment = addToArr(employeesDepartment, indDepartment, empl);
			
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
		return Arrays.copyOf(employeesAge, index);
	}
	
	private Employee[] getFilteredArr(Employee[] src, Employee from, Employee to, Comparator<Employee> comp) {
		int start = Arrays.binarySearch(src, from, comp.thenComparing(cmpId));
		int end = Arrays.binarySearch(src, to, comp.thenComparing(Comparator.reverseOrder()));
		
		try {
			return Arrays.copyOfRange(src, getValidIndex(start), getValidIndex(end));
		} catch (Exception e) {
			return new Employee[] {};
		}
	}
	
	private int getValidIndex(int index) {
		return index < 0 ? Math.abs(index) - 1 : index;
	}
	

	
	
}