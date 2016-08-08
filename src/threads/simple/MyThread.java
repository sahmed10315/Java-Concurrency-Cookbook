package threads.simple;

/* Simple implementation of creating a thread by extending Thread class.
 * main() method is executed as a separate thread, so we have two threads. */

public class MyThread extends Thread {
	public void run() {
		System.out.println("In run method: thread name : " + getName());
	}

	public static void main(String [] args) {
		Thread myThread = new MyThread();
		myThread.start();
		
		System.out.println("In main method: thread name: " + Thread.currentThread().getName());
	}
}
