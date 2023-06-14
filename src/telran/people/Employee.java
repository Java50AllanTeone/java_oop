package telran.people;

public class Employee implements Comparable<Employee> {
	
	private final int id;
	private int salary;
	private final int birthYear;
	
	private String name;
	private String department;
	
	
	public Employee(int id, int salary, int birthYear, String name, String department) {
		this.id = id;
		this.salary = salary;
		this.birthYear = birthYear;
		this.name = name;
		this.department = department;
	}
	
	public int getId() {
		return this.id;
	}

	public int getSalary() {
		return this.salary;
	}

	public int getBirthYear() {
		return this.birthYear;
	}

	public String getName() {
		return this.name;
	}

	public String getDepartment() {
		return this.department;
	}


	@Override
	public boolean equals(Object emplObj) {
		if (this == emplObj)
			return true;
		if (emplObj == null)
			return false;
		if (getClass() != emplObj.getClass())
			return false;
		Employee other = (Employee) emplObj;
		return id == other.id;
	}

	@Override
	public int compareTo(Employee empl) {
		
		return this.getId() - empl.getId();
	}

}
