package app;

import java.util.List;

import models.Bank;
import models.Customer;
import models.Employee;
import models.Store;

public class Main {
	public static void main(String[] args) {
		Bank bank = Bank.getInstance();
		List<Employee> employees = Utils.createEmployees(bank);
		List<Store> stores = Utils.createStores(employees, bank);
		List<Customer> customers = Utils.createCustomers(stores, bank);

		for(Employee employee: employees) {
			employee.start();
		}
		
		for(Customer customer: customers) {
			customer.start();
		}
		
		for(Employee employee: employees) {
			try {
				employee.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Utils.showBalances(employees, stores, customers);
		
	}
}
