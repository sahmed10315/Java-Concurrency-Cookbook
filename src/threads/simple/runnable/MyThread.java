package threads.simple.runnable;

/* Simple implementation of creating a thread by implementing Runnable interface.
 * main() method is executed as a separate thread, so we have two threads. */
public class MyThread implements Runnable {
	public void run() {
		System.out.println("In run method: thread name : " + Thread.currentThread().getName());
	}
	
	public static void main(String [] args) throws Exception {
		Thread myThread = new Thread(new MyThread());
		myThread.start();
		
		System.out.println("In main method: thread name : " + Thread.currentThread().getName());
	}

}
