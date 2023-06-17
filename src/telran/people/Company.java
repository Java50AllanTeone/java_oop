package telran.people;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import telran.people.test.EmployeeComparator;
import telran.people.test.EmployeePredicate;

public class Company {
	private Employee[] employees;
	
	private Employee[] employeesId;
	private Employee[] employeesSalary;
	private Employee[] employeesAge;
	private Employee[] employeesName;
	private Employee[] employeesDepartment;

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
		Employee[] res = getFilteredArray(employees, e -> e.getBirthYear() <= yearTo && e.getBirthYear() >= yearFrom);
		Arrays.sort(res, (e1, e2) -> e2.getBirthYear() - e1.getBirthYear());
		return res;
	}
	
	public Employee[] getEmployeesBySalary(int salaryFrom, int salaryTo) {
		Employee[] res = getFilteredArray(employees, e -> e.getSalary() <= salaryTo && e.getSalary() >= salaryFrom);
		Arrays.sort(res, (e1, e2) -> e1.getSalary() - e2.getSalary());
		return res;
	}
	
	public Employee[] getEmployeesByDepartment(String department) {
		Employee[] res = getFilteredArray(employees, e -> e.getDepartment().equals(department));
		Arrays.sort(res, (e1, e2) -> e1.getId() - e2.getId());
		return res;
	}
	
	public boolean addEmployee(Employee empl) {
		Predicate<Employee> pred = e -> e.getId() == empl.getId();
		
		for (int i = 0; i < employees.length; i++) {
			if (pred.test(employees[i])) {
				return false;
			}
		}
		employees = Arrays.copyOf(employees, employees.length + 1);
		employees[employees.length - 1] = empl;
		return true;
	}
	
	public boolean removeEmployeesIf(Predicate<Employee> predicate) {
		predicate = predicate.negate();
		int prevSize = employees.length;
		employees = getFilteredArray(employees, predicate);
	
		return prevSize > employees.length;
	}
	
	public Employee getEmployee(int id) {
		Predicate<Employee> pred = e -> e.getId() == id;
		Employee res = null;
		
		for (Employee empl : employees) {
			if (pred.test(empl)) {
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
