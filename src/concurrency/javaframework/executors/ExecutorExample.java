package concurrency.javaframework.executors;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class ThreadPrinter implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("From the new thread: " + Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class ExecutorExample {

	public static void main(String args[]) throws InterruptedException {
		final Executor executor = Executors.newCachedThreadPool();
		executor.execute(new ThreadPrinter());
		executor.execute(new ThreadPrinter());
		executor.execute(new ThreadPrinter());
		}
}