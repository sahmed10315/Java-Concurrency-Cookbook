package threads.simple;

/* Three states of a thread are new, runnable, and terminated */
public class ThreadStates {

	public static void main(String [] args) throws InterruptedException {
		Thread t = new Thread();
		
		System.out.println("After creating thread: State " + t.getState());
		t.start();
		System.out.println("After starting thread: State " + t.getState());
		t.join(); // main method waits for the thread t to die.
		System.out.println("After calling join thread: State " + t.getState());
	}
}
