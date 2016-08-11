package concurrency.javaframework.semaphore;

import java.util.concurrent.Semaphore;

/* A semaphore controls access to shared resources. If no of resources is 1, sempahore acts like a lock */

public class ATMRoom {

	public static void main(String [] args) {
		Semaphore machines = new Semaphore(2);
		
		new Person(machines, "Mickey");
		new Person(machines, "Donald");
		new Person(machines, "Tom");
		new Person(machines, "Jerry");
		new Person(machines, "Casper");
	}
}

class Person extends Thread {
	
	private Semaphore machines; 
	
	public Person(Semaphore machines, String name) {
		this.machines = machines;
		this.setName(name);
		this.start(); 
	}
	
	public void run() {
		try { 
			System.out.println(getName() + " waiting to access an ATM machine");
			machines.acquire();
			System.out.println(getName() + " is accessing an ATM machine");
			Thread.sleep(5000); // simulate the time required for withdrawing amount 
			System.out.println(getName() + " is done using the ATM machine");
			machines.release();
		} catch(InterruptedException ie) {
			System.err.println(ie);
		}
	}
}