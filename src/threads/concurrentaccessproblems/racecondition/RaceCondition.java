package threads.concurrentaccessproblems.racecondition;

/* When two or more threads are trying to access a variable and one of them wants to modify it , we get a race condition.
 * To fix this problem we have to ensure a single thread does the write and read atomically. */
class Counter {
	public static long count = 0;
}

class UseCounterDataRace implements Runnable {
	
	public void  run() {
		for (int i = 0 ; i < 3; i++) {
			Counter.count++;
			System.out.println(Thread.currentThread().getName() + ": counter =  " + Counter.count + " ");
		}
	}
}

class UseCounterSynchrnoized implements Runnable {
	
	public synchronized void run() {
		for (int i = 0 ; i < 3; i++) {
			Counter.count++;
			System.out.println(Thread.currentThread().getName() + ": counter =  " + Counter.count + " ");
		}
	}
}

public class RaceCondition {

	public static void main(String [] args) throws InterruptedException {
		UseCounterDataRace c = new UseCounterDataRace();
		
		Thread t1 = new Thread(c);
		Thread t2 = new Thread(c);
		Thread t3 = new Thread(c);
		
		t1.start();
		t2.start();
		t3.start();
		
		t1.join();
		t2.join();
		t3.join();
		
		Counter.count = 0;
		UseCounterSynchrnoized cs = new UseCounterSynchrnoized();
		
		Thread t4 = new Thread(cs);
		Thread t5 = new Thread(cs);
		Thread t6 = new Thread(cs);
		
		t4.start();
		t5.start();
		t6.start();
	}
}
