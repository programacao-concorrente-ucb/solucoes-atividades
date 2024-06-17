package models;

public class Employee extends Person {
	private BankAccount investimentAccount;
	private EmployeeStatus employeeStatus;

	
	public Employee(String name, 
					BankAccount salaryAccount, 
					BankAccount investimentAccount) {
		super(name, salaryAccount);
		this.investimentAccount = investimentAccount;
	}
	
	public void run() {
		while(true) {
			System.out.println(this.getName() + " trabalha y trabalha");
			wait(3);
			if(gotPaid()) {
				System.out.println(this.getName() + ": Money trees is the perfect place for shade And that's jus' how I feel");
				invest();
				break;
			} else if(employeeStatus == EmployeeStatus.OFF_DUTY) {
				System.out.println(this.getName() + ": Back to reality, we poor, ya bish ");
				break;
			}
			
		}
		

		
	}
	
	public void goHome() {
		employeeStatus = EmployeeStatus.OFF_DUTY;
	}
	
	public BankAccount getInvestimentAccount() {
		return investimentAccount;
	}
	
	public boolean gotPaid() {
		return this.getBankAccount().getBalance() > 0;
	}
	
	public void invest() {
		System.out.println(this.getName() + " investindo...");
		Bank bank = Bank.getInstance();
		bank.transfer(0.2*getBankAccount().getBalance(), 
						getBankAccount().getAccountId(),
						getInvestimentAccount().getAccountId());
	}
	
}
