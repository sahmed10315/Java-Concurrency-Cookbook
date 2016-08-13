package concurrency.javaframework.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SimpleCounter {

	private int number = 0;

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}
}

public class LockedSharedState {

	public void lockedSharedState() {
		final ExecutorService executorService = Executors.newCachedThreadPool();

		final SimpleCounter c = new SimpleCounter();
		executorService.execute(new CounterSetter(c));

		synchronized (c) {
			c.setNumber(200);
		}
	}

	private static class CounterSetter implements Runnable {
		private final SimpleCounter counter;

		private CounterSetter(SimpleCounter counter) {
			this.counter = counter;
		}

		@Override
		public void run() {
			while (true) {
				synchronized (counter) {
					counter.setNumber(100);
				}
			}
		}
	}
}