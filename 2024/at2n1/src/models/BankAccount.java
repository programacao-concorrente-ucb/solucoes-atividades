package models;

import publisher.EventManager;

public class BankAccount {
	private double balance;
	private int accountId;
    private EventManager events;

	
	public BankAccount(int accountId, double initialBalance) {
		this.accountId = accountId;
		this.balance = initialBalance;
		this.events = new EventManager("deposit", "withdraw");
	}
	
	public EventManager getEvents() {
		return events;
	}
	
	public int getAccountId() {
		return accountId;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void deposit(double value) {
		setBalance(this.balance + value);
		events.notify("deposit", value);
	}
	
	public void withdraw(double value) {
		setBalance(this.balance - value);
		events.notify("withdraw", value);
	}
	
}
