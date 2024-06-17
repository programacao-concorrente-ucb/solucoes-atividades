package models;

import java.util.List;

public class Store {
	private String name;
	private BankAccount bankAccount;
	private List<Employee> employees;
	private int numCustomers = 0;
	
	public Store(String name, List<Employee> employees, BankAccount bankAccount) {
		this.setName(name);
		this.setEmployees(employees);
		this.setBankAccount(bankAccount);
	}
	
	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void payEmployees() {
		Bank bank = Bank.getInstance();
		if(bankAccount.getBalance() >= 1400) {
			for(Employee employee: employees) {
				if(!employee.gotPaid()) {
					bank.transfer(1400, bankAccount.getAccountId(), employee.getBankAccount().getAccountId());
				}
			}
		}
	}
	
	public synchronized void enterStore() {
		numCustomers++;
		System.out.println("Novo cliente na " + this.getName() + "! " + numCustomers + " cliente(s) comprando no momento...");
	}
	
	public void closeStore() {
		releaseEmployees();
	}
	
	public synchronized void leaveStore() {
		numCustomers--;
		if(numCustomers == 0) {
			System.out.println("Não há mais clientes para atender. Loja irá fechar...");
			closeStore();
		}
	}
	
	public void releaseEmployees() {
		for(Employee employee: employees) {
			employee.goHome();
		}
	}
}
