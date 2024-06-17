package models;

import java.util.List;
import java.util.Random;

public class Customer extends Person {
	private List<Store> stores;
	
	public Customer(String name, List<Store> stores, BankAccount bankAccount) {
		super(name, bankAccount);
		this.stores = stores;
	}
	
	public void run() {
		BankAccount customerAccount = this.getBankAccount();
		Bank bank = Bank.getInstance();
		startPurchases();
		
		int currentStore = selectStore(0);
		while(customerAccount.getBalance() > 0) {
			wait(new Random().nextInt(5) + 3);
			System.out.println(this.getName() + " (conta " + this.getBankAccount().getAccountId() + ")" +  " compra y compra");
			double value = getPurchaseValue();
			Store store = stores.get(currentStore);
			currentStore = selectStore(currentStore);
			bank.transfer(value, getBankAccount().getAccountId(), store.getBankAccount().getAccountId());
		}
		
		finishPurchases();
	}
	
	public void startPurchases() {
		for(Store store: stores) {
			store.enterStore();
		}
	}
	
	public void finishPurchases() {
		for(Store store: stores) {
			store.leaveStore();
		}
	}
	
	public double getPurchaseValue() {
		double purchaseValues[] = {100.0, 200.0};
        Random rand = new Random();
        int randomIndex = rand.nextInt(purchaseValues.length);
        
        // retorna menor valor entre [100, 200] e valor no saldo da conta do cliente
        return Math.min(purchaseValues[randomIndex], getBankAccount().getBalance());
	}
	
	public int selectStore(int currentStore) {
		return (currentStore + 1)%2;
	}
	
	
}
