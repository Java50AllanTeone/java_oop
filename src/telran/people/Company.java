package telran.people;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class Company {
	private Employee[] employees;
	
	public Employee[] employeesId;
	public Employee[] employeesSalary;
	public Employee[] employeesAge;
	public Employee[] employeesName;
	public Employee[] employeesDepartment;

	public Company(Employee[] employees) {
		this.employees = Arrays.copyOf(employees, employees.length);
		
		this.employeesId = Arrays.copyOf(employees, employees.length);
		this.employeesSalary = Arrays.copyOf(employees, employees.length);
		this.employeesAge = Arrays.copyOf(employees, employees.length);
		this.employeesName = Arrays.copyOf(employees, employees.length);
		this.employeesDepartment = Arrays.copyOf(employees, employees.length);
		
		Arrays.sort(employeesId, (e1, e2) -> e1.getId() - e2.getId());
		Arrays.sort(employeesSalary, (e1, e2) -> e1.getSalary() - e2.getSalary());
		Arrays.sort(employeesAge, (e1, e2) -> e2.getBirthYear() - e1.getBirthYear());
		Arrays.sort(employeesName, (e1, e2) -> e1.getName().compareTo(e2.getName()));
		Arrays.sort(employeesDepartment, (e1, e2) -> e1.getDepartment().compareTo(e2.getDepartment()));
	}
	
	public Employee[] getAllEmployees() {
		return Arrays.copyOf(employeesId, employeesId.length);
	}
	
	public Employee[] getAllEmployeesByAge(int yearFrom, int yearTo) {
		Employee[] res = getFilteredArray(employees, e -> e.getBirthYear() <= yearTo && e.getBirthYear() >= yearFrom);
		//Arrays.sort(res, (e1, e2) -> e2.getBirthYear() - e1.getBirthYear());
		
		
		
		return getFilteredArray(employeesAge, (e1, e2) -> e1.getBirthYear() - e2.getBirthYear(), yearFrom, yearTo);
		
		
		//return res;
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
		boolean res = false;

		var indId = Arrays.binarySearch(employeesId, empl, (e1, e2) -> e1.getId() - e2.getId());
		var indSalary = Arrays.binarySearch(employeesSalary, empl, (e1, e2) -> e1.getSalary() - e2.getSalary());
		var indAge = Arrays.binarySearch(employeesAge, empl, (e1, e2) -> e2.getBirthYear() - e1.getBirthYear());
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
	
	private Employee[] getFilteredArray(Employee[] src, Predicate<Employee> predicate) {
		Employee[] res = new Employee[src.length];
		int index = 0;
		
		for (int i = 0; i < src.length; i++) {
			if (predicate.test(src[i])) {
				res[index++] = src[i];
			}
		}
		return Arrays.copyOf(res, index);
	}
	
	private Employee[] getFilteredArray(Employee[] src, Comparator<Employee> comp, int from, int to) {
		int fr = binarySearchFirst(src, comp, new Employee(0, 0, from, "", ""));
		int t = binarySearchLast(src, comp, new Employee(0, 0, to, "", ""));
		System.out.println(fr);
		System.out.println(t);
		
		return Arrays.copyOfRange(src, fr, to);
		
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
	
	
	
	public static int binarySearchFirst(Employee[] array, Comparator<Employee> comp, Employee empl) {
		int left = 0;
		int right = array.length - 1;
		int middle = right / 2;
		
		while(left < right) {
			if (comp.compare(empl, array[middle]) > 0) {
				System.out.println(empl);
				System.out.println(array[middle]);
				System.out.println(comp.compare(empl, array[middle]));
				right = middle - 1;
			} else if (comp.compare(empl, array[middle]) < 0) {
				left = middle + 1;
			} else {
				right = middle;
			}
			middle = (left + right) / 2;
		}
		
		if (comp.compare(empl, array[middle]) != 0)
			middle = comp.compare(empl, array[middle]) > 0 ? -(middle + 2) : -(middle + 1);
		
		return middle;
	}
	
	
	//to fix
	public static int binarySearchLast(Employee[] array, Comparator<Employee> comp, Employee empl) {
		int left = 0;
		int right = array.length - 1;
		int middle = right / 2;
		
		while(left < right) {
			if (comp.compare(empl, array[middle]) > 0) {
				right = middle - 1;
			} else if (comp.compare(empl, array[middle]) < 0) {
				left = middle + 1;
			} else {
				left = middle;
			}
			middle = (left + right) / 2;
		}
		
		if (comp.compare(empl, array[middle]) != 0)
			middle = comp.compare(empl, array[middle]) > 0 ? -(middle + 2) : -(middle + 1);
		
		return middle;
	}
	
	
	
	

}
