package threads.examples.waitnotify.coffeemachine;

public class Waiter extends Thread {
	public void getCoffee() {
		synchronized(CoffeeMachine.lock) {
			if(CoffeeMachine.coffeeMade == null) {
				try {
					// wait till the CoffeeMachine says (notifies) that coffee is ready 
					System.out.println("Waiter: Will get orders till coffee machine notifies me "); 
					CoffeeMachine.lock.wait(); 
				}
				catch(InterruptedException ie) { 
					ie.printStackTrace();
				}
			}
			System.out.println("Waiter: Delivering " + CoffeeMachine.coffeeMade);
			CoffeeMachine.coffeeMade = null;
			// ask (notify) the coffee machine to prepare the next coffee 
			CoffeeMachine.lock.notifyAll(); 
			System.out.println("Waiter: Notifying coffee machine to make another one"); 
		}
	}
	
	public void run() {
		while(true) {
			getCoffee(); 
		}
	}
}