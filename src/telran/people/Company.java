package telran.people;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class Company {	
	public Employee[] employeesId;
	public Employee[] employeesSalary;
	public Employee[] employeesAge;
	public Employee[] employeesName;
	public Employee[] employeesDepartment;

	public Company(Employee[] employees) {
		this.employeesId = Arrays.copyOf(employees, employees.length);
		this.employeesSalary = Arrays.copyOf(employees, employees.length);
		this.employeesAge = Arrays.copyOf(employees, employees.length);
		this.employeesName = Arrays.copyOf(employees, employees.length);
		this.employeesDepartment = Arrays.copyOf(employees, employees.length);
		
		Arrays.sort(employeesId, (e1, e2) -> e1.getId() - e2.getId());
		Arrays.sort(employeesSalary, (e1, e2) -> e1.getSalary() - e2.getSalary());
		Arrays.sort(employeesAge, (e1, e2) -> e1.getBirthYear() - e2.getBirthYear());
		Arrays.sort(employeesName, (e1, e2) -> e1.getName().compareTo(e2.getName()));
		Arrays.sort(employeesDepartment, (e1, e2) -> e1.getDepartment().compareTo(e2.getDepartment()));
	}
	
	public Employee[] getAllEmployees() {
		return Arrays.copyOf(employeesId, employeesId.length);
	}
	
	public Employee[] getAllEmployeesByAge(int yearFrom, int yearTo) {
		return getFilteredArray(employeesAge, e -> e.getBirthYear() >= yearFrom && e.getBirthYear() <= yearTo);
	}
	
	public Employee[] getEmployeesBySalary(int salaryFrom, int salaryTo) {
		return getFilteredArray(employeesSalary, e -> e.getSalary() <= salaryTo && e.getSalary() >= salaryFrom);
	}
	
	public Employee[] getEmployeesByDepartment(String department) {
		return getFilteredArray(employeesDepartment, e -> e.getDepartment().equals(department));
	}
	
	public boolean addEmployee(Employee empl) {
		boolean res = false;

		var indId = Arrays.binarySearch(employeesId, empl, (e1, e2) -> e1.getId() - e2.getId());
		var indSalary = Arrays.binarySearch(employeesSalary, empl, (e1, e2) -> e1.getSalary() - e2.getSalary());
		var indAge = Arrays.binarySearch(employeesAge, empl, (e1, e2) -> e1.getBirthYear() - e2.getBirthYear());
		var indName = Arrays.binarySearch(employeesName, empl, (e1, e2) -> e1.getName().compareTo(e2.getName()));
		var indDepartment = Arrays.binarySearch(employeesDepartment, empl, (e1, e2) -> e1.getDepartment().compareTo(e2.getDepartment()));
		
		if (indId < 0) {

			indId = getValidIndex(indId);
			indSalary = getValidIndex(indSalary);
			indAge = getValidIndex(indAge);
			indName = getValidIndex(indName);
			indDepartment = getValidIndex(indDepartment);
			
			employeesId = add(employeesId, indId, empl);
			employeesSalary = add(employeesSalary, indSalary, empl);
			employeesAge = add(employeesAge, indAge, empl);
			employeesName = add(employeesName, indName, empl);
			employeesDepartment = add(employeesDepartment, indDepartment, empl);
			
			res = true;
		}
		return res;
	}


	public boolean removeEmployeesIf(Predicate<Employee> predicate) {
		predicate = predicate.negate();
		int prevSize = employeesId.length;
		
		employeesId = getFilteredArray(employeesId, predicate);
		employeesSalary = getFilteredArray(employeesSalary, predicate);
		employeesAge = getFilteredArray(employeesAge, predicate);
		employeesName = getFilteredArray(employeesName, predicate);
		employeesDepartment = getFilteredArray(employeesDepartment, predicate);
	
		return prevSize > employeesId.length;
	}
	
	public Employee getEmployee(int id) {
		return getFilteredArray(employeesId, e -> e.getId() == id)[0];
	}
	
	
	
	private Employee[] getFilteredArray(Employee[] src, Predicate<Employee> predicate) {
		Employee[] res = new Employee[src.length];
		int index = 0;
		
		for (int i = 0; i < src.length; i++) {
			if (predicate.test(src[i])) {
				res[index++] = src[i];
			}
		}
		return Arrays.copyOf(employeesAge, index);
	}
	
	private int getValidIndex(int index) {
		return index < 0 ? Math.abs(index) - 1 : index;
	}
	
	private Employee[] add(Employee[] arr, int ind, Employee empl) {
		Employee[] res = new Employee[arr.length + 1];
		System.arraycopy(arr, 0, res, 0, ind);
		
		System.arraycopy(arr, ind, res, ind + 1, arr.length - ind);
		res[ind] = empl;
		return res;
	}
	
	
}
