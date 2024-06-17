package factories;

import java.util.List;

import models.BankAccount;
import models.Customer;
import models.Store;

public class CustomerFactory extends PersonFactory {
	
	public static Customer createCustomer(List<Store> stores, BankAccount bankAccount) {
		return new Customer(generateRandomName(), stores, bankAccount);
	}
}
