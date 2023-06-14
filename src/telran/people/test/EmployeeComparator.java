package telran.people.test;

import java.util.Comparator;

import telran.people.Employee;

public class EmployeeComparator implements Comparator<Employee> {
	String criterion;

	public EmployeeComparator(String criterion) {
		this.criterion = criterion;
	}


	@Override
	public int compare(Employee e1, Employee e2) {

		switch(criterion) {
		case "age" : return e2.getBirthYear() - e1.getBirthYear();
		case "salary" : return e1.getSalary() - e2.getSalary();
		case "id" : return e1.getId() - e2.getId();
		default: throw new Error();
		}
	}
	

}
