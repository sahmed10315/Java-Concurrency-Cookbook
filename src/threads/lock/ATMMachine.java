package threads.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Each Person is an independent thread; their access to the common resource 
//needs to be synchronized using a lock  
class Person extends Thread {
	private Lock machine;

	public Person(Lock machine, String name) {
		this.machine = machine;
		this.setName(name);
		this.start();
	}

	public void run() {
		try {
			System.out.println(getName() + " waiting to access an ATM machine");
			machine.lock();
			System.out.println(getName() + " is accessing an ATM machine");
			Thread.sleep(1000); // simulate the time required for withdrawing
								// amount
		} catch (InterruptedException ie) {
			System.err.println(ie);
		} finally {
			System.out.println(getName() + " is done using the ATM machine");
			machine.unlock();
		}
	}
}

public class ATMMachine {
	public static void main(String[] args) {
		// A person can use a machine again, and hence using a "reentrant lock"
		Lock machine = new ReentrantLock();

		// list of people waiting to access the machine
		new Person(machine, "Mickey");
		new Person(machine, "Donald");
		new Person(machine, "Tom");
		new Person(machine, "Jerry");
		new Person(machine, "Casper");
	}
}