package models;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
	private static int nextAccountId = 1;
    private static Map<Integer, BankAccount> bankAccounts = new HashMap<>();
    private Lock transferLock;
    private static Bank instance;
    
    
    private Bank() {
    	this.transferLock = new ReentrantLock();
    }
    
    public static synchronized Bank getInstance() {
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }
    
	public void transfer(double transferValue, int originAccountId, int destAccountId) {
		if(originAccountId > nextAccountId) {
			System.out.println("Conta de origem não existe!!!");
		}
		if (destAccountId > nextAccountId) {
			System.out.println("Conta de destino não existe!!!");
		}
    	boolean originAccountHasSufficientBalance = checkBalance(originAccountId, transferValue);
    	
    	if(originAccountHasSufficientBalance) {
    		BankAccount originBankAccount = bankAccounts.get(originAccountId);
    		BankAccount destBankAccount = bankAccounts.get(destAccountId);
    		
    		try {
				transferLock.lock();
				System.out.println("Realizando transferência da conta " + originAccountId + " para conta " + destAccountId);
	    		originBankAccount.withdraw(transferValue);
	    		destBankAccount.deposit(transferValue);
	    		transferLock.unlock();
			} catch (Exception e) {
				e.printStackTrace();
			}

		
    	}
	}

	public BankAccount createBankAccount(double initialBalance) {
		BankAccount bankAccount = new BankAccount(getNextId(), initialBalance);
		bankAccounts.put(bankAccount.getAccountId(), bankAccount);
		return bankAccount;
	}
	
    public static int getNextId() {
        return nextAccountId++;
    }
       
    
    private static boolean checkBalance(int accountId, double transferValue) {
    	return bankAccounts.get(accountId).getBalance() >= transferValue;
    }    
}
