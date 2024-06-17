package listeners;

import models.Store;

public class StorePaymentsListener implements EventListener {
	private Store store;
	
	public StorePaymentsListener(Store store) {
		this.store = store;
	}
	
	@Override
	public void update(String eventType, double value) {
        System.out.println("Pagamento de " + value + " para loja/conta: " + store.getName() + "/" + store.getBankAccount().getAccountId());
        store.payEmployees();
	}
}
