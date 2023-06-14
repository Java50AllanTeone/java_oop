package telran.people.test;

import java.util.function.Predicate;

import telran.people.Employee;


public class EmployeePredicate implements Predicate<Employee> {
	int from;
	int to;
	String department;
	String criterion;
	
	public EmployeePredicate(int from, int to, String criterion) {
		this.criterion = criterion;
		this.from = from;
		this.to = to;
	}
	
	public EmployeePredicate(String department, String criterion) {
		this.criterion = criterion;
		this.department = department;
	}
	
	public EmployeePredicate(int id, String criterion) {
		this.criterion = criterion;
		this.from = id;
	}

	@Override
	public boolean test(Employee e) {

		switch(criterion) {
		
		case "year" : return e.getBirthYear() >= this.from && e.getBirthYear() <= this.to;
		case "salary" : return e.getSalary() >= this.from && e.getSalary() <= this.to;
		case "department" : return e.getDepartment().equals(department);
		case "id" : return e.getId() == from;
		default: throw new RuntimeException();
		}
	}
}
