package models;

public abstract class Person extends Thread {
	private BankAccount bankAccount;
	
	public Person(String name, BankAccount bankAccount) {
		super(name);
		this.bankAccount = bankAccount;
	}
	
	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void wait(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
