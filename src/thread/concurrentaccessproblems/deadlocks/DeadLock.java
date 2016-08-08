package thread.concurrentaccessproblems.deadlocks;

/* Deadlock describes a situation where two or more threads are blocked forever, waiting for each other.
 * Deadlock occurs when multiple threads need the same locks but obtain them in different order.
 * If a thread acquires a lock on resource r1, and waits to acquire another lock on resource r2. At the same time , another thread
 * already acquired r2 and is waiting to obtain a lock on r1. Neither of the thread can proceed until the other one releases the lock.
 *  
 * To fix Deadlock we have to make sure that multiple locks, are acquired in the same order everywhere.
 */
class Balls {
	public static long balls = 0;
}

class Runs {
	public static long runs = 0;
}

class Counter implements Runnable {
	public void incrementBallAfterRun() {
		synchronized (Runs.class) {
			synchronized (Balls.class) {
				Runs.runs++;
				Balls.balls++;
			}
		}
	}
   // To fix dead lock Runs.class should be acquired first then Balls.class
	public void incrementRunAfterBall() {
		synchronized (Balls.class) {
			synchronized (Runs.class) {
				Balls.balls++;
				Runs.runs++;
			}
		}
	}

	public void run() {
		incrementBallAfterRun();
		incrementRunAfterBall();
	}
}

public class DeadLock {
	public static void main(String[] args) throws InterruptedException {
		Counter c = new Counter();
		Thread t1 = new Thread(c);
		t1.start();
		Thread t2 = new Thread(c);
		t2.start();

		System.out.println("Waiting for threads to complete execution...");
		t1.join();
		t2.join();

		System.out.println("Done.");
	}
}
