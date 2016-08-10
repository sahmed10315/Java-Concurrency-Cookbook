package threads.examples.waitnotify.coffeemachine;

public class CoffeeShop {
	public static void main(String []args) {
		CoffeeMachine coffeeMachine = new CoffeeMachine(); 
		Waiter waiter = new Waiter();
		coffeeMachine.start();
		waiter.start();
	}
}