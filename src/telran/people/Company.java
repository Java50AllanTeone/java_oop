package telran.people;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import telran.points.Point;

public class Company {

	private Employee[] employees;

	public Company(Employee[] employees) {
		this.employees = Arrays.copyOf(employees, employees.length);
	}
	
	public Employee[] getAllEmployees(Comparator<Employee> comparator) {
		Employee [] res = Arrays.copyOf(employees, employees.length);
		Arrays.sort(res, comparator);
		return res;
	}
	
	public Employee[] getAllEmployeesByAge(int yearFrom, int yearTo) {
		Predicate<Employee> predYear = e -> e.getBirthYear() <= yearTo && e.getBirthYear() >= yearFrom;
		Comparator<Employee> compAge = (e1, e2) -> e2.getBirthYear() - e1.getBirthYear();
		
		Employee[] res = new Employee[employees.length];
		int index = 0;
		
		for (int i = 0; i < employees.length; i++) {
			if (predYear.test(employees[i])) {
				res[index++] = employees[i];
			}
		}
		res = Arrays.copyOf(res, index);
		Arrays.sort(res, compAge);
		return res;
	}
	
	public Employee[] getEmployeesBySalary(int salaryFrom, int salaryTo) {
		Predicate<Employee> predSalary = e -> e.getBirthYear() <= salaryTo && e.getBirthYear() >= salaryFrom;
		Comparator<Employee> compSalary = (e1, e2) -> e1.getSalary() - e2.getSalary();
		
		Employee[] res = new Employee[employees.length];
		int index = 0;
		
		for (int i = 0; i < employees.length; i++) {
			if (predSalary.test(employees[i])) {
				res[index++] = employees[i];
			}
		}
		res = Arrays.copyOf(res, index);
		Arrays.sort(res, compSalary);
		return res;
	}
	
	public Employee[] getEmployeesByDepartment(String department) {
		Predicate<Employee> predDepartment = e -> e.getDepartment().equals(department);
		Comparator<Employee> compId = (e1, e2) -> e1.getId() - e2.getId();
		
		Employee[] res = new Employee[employees.length];
		int index = 0;
		
		for (int i = 0; i < employees.length; i++) {
			if (predDepartment.test(employees[i])) {
				res[index++] = employees[i];
			}
		}
		res = Arrays.copyOf(res, index);
		Arrays.sort(res, compId);
		return res;
	}
	
	public boolean addEmployee(Employee empl) {
		Predicate<Employee> predId = e -> e.getId() == empl.getId();
	
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
		Predicate<Employee> predId = e -> e.getId() == id;
		Employee res = null;
		
		for (Employee empl : employees) {
			if (predId.test(empl)) {
				res = empl;
			}
		}
		return res;
	}
	
	
	
	
	
	

}
