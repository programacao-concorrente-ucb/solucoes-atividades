package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import factories.CustomerFactory;
import factories.EmployeeFactory;
import listeners.StorePaymentsListener;

public class Main {
	public static final int NUM_CUSTOMERS = 5;
	public static final int NUM_EMPLOYEES = 4;
	
	public static void main(String[] args) {
		Bank bank = Bank.getInstance();
		List<Employee> employees = createEmployees(bank);
		List<Store> stores = createStores(employees, bank);
		List<Customer> customers = createCustomers(stores, bank);

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
		
		showBalances(employees, stores, customers);
		
	}
	
	public static List<Store> createStores(List<Employee> employees, Bank bank) {
		List<Store> stores = Arrays.asList(
				new Store("Leitura", employees.subList(0, 2), bank.createBankAccount(0)),
				new Store("Livraria da Travessa", employees.subList(2, 4), bank.createBankAccount(0))
		);
		
		for(Store store: stores) {
			store.getBankAccount().getEvents().subscribe("deposit", new StorePaymentsListener(store));
		}
		
		return stores;
	}
	public static List<Employee> createEmployees(Bank bank) {
		List<Employee> employees = new ArrayList<Employee>();
		
		for(int i = 0;i < NUM_EMPLOYEES;i++) {
			BankAccount salaryAccount = bank.createBankAccount(0);
			BankAccount investimentAccount = bank.createBankAccount(0);
			employees.add(EmployeeFactory.createEmployee("oi", salaryAccount, investimentAccount));
		}
		
		return employees;
	}
	
	public static List<Customer> createCustomers(List<Store> stores, Bank bank) {
		List<Customer> customers = new ArrayList<Customer>();
		
		for(int i = 0;i < NUM_CUSTOMERS;i++) {
			BankAccount account = bank.createBankAccount(1000);
			customers.add(CustomerFactory.createCustomer(stores, account));
		}
		
		return customers;
	}
	
	public static void showBalances(List<Employee> employees, 
									List<Store> stores,
									List<Customer> customers) {
		System.out.println("=================================================");
		System.out.println("Saldo dos clientes:");
		for(Customer customer: customers) {
			System.out.println(customer.getName() + ":");
			System.out.println("Conta: R$ " + customer.getBankAccount().getBalance());
			System.out.println("=================================================");
		}
		
		System.out.println("=================================================");
		System.out.println("Saldo das lojas:");
		for(Store store: stores) {
			System.out.println(store.getName() + ":");
			System.out.println("Conta salário: R$ " + store.getBankAccount().getBalance());
			System.out.println("=================================================");
		}
		
		System.out.println("=================================================");
		System.out.println("Saldo dos funcionários:");
		for(Employee employee: employees) {
			System.out.println(employee.getName() + ":");
			System.out.println("Conta salário: R$ " + employee.getBankAccount().getBalance());
			System.out.println("Conta de investimento: R$ " + employee.getInvestimentAccount().getBalance());
			System.out.println("=================================================");
		}
	}
}
