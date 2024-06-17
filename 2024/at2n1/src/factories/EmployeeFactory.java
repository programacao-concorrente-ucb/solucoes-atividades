package factories;

import models.BankAccount;
import models.Employee;

public class EmployeeFactory extends PersonFactory {
	public static Employee createEmployee(String name, 
										  BankAccount salaryAccount, 
										  BankAccount investimentAccount){
		return new Employee(generateRandomName(), salaryAccount, investimentAccount);
	}
}
