package telran.people;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import telran.people.test.EmployeeComparator;
import telran.people.test.EmployeePredicate;

public class Company {
//	Predicate<Employee> predYear = e -> e.getBirthYear() <= yearTo && e.getBirthYear() >= yearFrom;
//	Predicate<Employee> predSalary = e -> e.getSalary() <= salaryTo && e.getBirthYear() >= salaryFrom;
//	Predicate<Employee> predDepartment = e -> e.getDepartment().equals(department);
//	Predicate<Employee> predId = e -> e.getId() == empl.getId();
//	Comparator<Employee> compAge = (e1, e2) -> e2.getBirthYear() - e1.getBirthYear();
//	Comparator<Employee> compSalary = (e1, e2) -> e1.getSalary() - e2.getSalary();
//	Comparator<Employee> compId = (e1, e2) -> e1.getId() - e2.getId();

	private Employee[] employees;

	public Company(Employee[] employees) {
		this.employees = Arrays.copyOf(employees, employees.length);
	}
	
	public Employee[] getAllEmployees(Comparator<Employee> comparator) {
		Employee [] res = Arrays.copyOf(employees, employees.length);
		Arrays.sort(res, comparator);
		return res;
	}
	
	public Employee[] getAllEmployees() {
		Employee [] res = Arrays.copyOf(employees, employees.length);
		Arrays.sort(res);
		return res;
	}
	
	public Employee[] getAllEmployeesByAge(int yearFrom, int yearTo) {
		var predYear = new EmployeePredicate(yearFrom, yearTo, "year");
		var compAge = new EmployeeComparator("age");
		
		Employee[] res = getFilteredArray(employees, predYear);
		Arrays.sort(res, compAge);
		return res;
	}
	
	public Employee[] getEmployeesBySalary(int salaryFrom, int salaryTo) {
		var predSalary = new EmployeePredicate(salaryFrom, salaryTo, "salary");
		var compSalary = new EmployeeComparator("salary");

		Employee[] res = getFilteredArray(employees, predSalary);
		Arrays.sort(res, compSalary);
		return res;
	}
	
	public Employee[] getEmployeesByDepartment(String department) {
		var predDepartment = new EmployeePredicate(department, "department");
		var compId = new EmployeeComparator("id");

		Employee[] res = getFilteredArray(employees, predDepartment);
		Arrays.sort(res, compId);
		return res;
	}
	
	public boolean addEmployee(Employee empl) {
		var predId = new EmployeePredicate(empl.getId(), "id");
	
		for (int i = 0; i < employees.length; i++) {
			if (predId.test(employees[i])) {
				return false;
			}
		}
		employees = Arrays.copyOf(employees, employees.length + 1);
		employees[employees.length - 1] = empl;
		return true;
	}
	
	public boolean removeEmployeesIf(Predicate<Employee> predicate) {
//		Predicate<Employee> predInverted = e -> !predicate.test(e);
//		int prevSize = employees.length;
//		employees = getFilteredArray(employees, predInverted);
		
		int prevSize = employees.length;
		Employee[] tmp = new Employee[prevSize];
		int index = 0;
		
		for(int i = 0; i < prevSize; i++) {
			if(!predicate.test(employees[i])) {
				tmp[index++] = employees[i];
			}
		}
		employees = Arrays.copyOf(tmp, index);
		return prevSize > employees.length;
	}
	
	public Employee getEmployee(int id) {
		var predId = new EmployeePredicate(id, "id");
		Employee res = null;
		
		for (Employee empl : employees) {
			if (predId.test(empl)) {
				res = empl;
			}
		}
		return res;
	}
	
	
	public <T> T[] getFilteredArray(T[] src, Predicate<T> predicate) {
		T[] res = Arrays.copyOf(src, src.length);
		int index = 0;
		
		for (int i = 0; i < src.length; i++) {
			if (predicate.test(src[i])) {
				res[index++] = src[i];
			}
		}
		return Arrays.copyOf(res, index);
	}
	
	
	

}
