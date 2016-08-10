package concurrency.javaframework.countdownlatch;

import java.util.concurrent.CountDownLatch;

class Runner extends Thread {
	private CountDownLatch timer;

	public Runner(CountDownLatch cdl, String name) {
		timer = cdl;
		this.setName(name);
		System.out.println(this.getName() + " ready and waiting for the count down to start");
		start();
	}

	public void run() {
		try {
			// wait for the timer count down to reach 0
			timer.await();
		} catch (InterruptedException ie) {
			System.err.println("interrupted -- can't start running the race");
		}
		System.out.println(this.getName() + " started running");
	}
}

public class RunningRaceStarter {
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch counter = new CountDownLatch(10);
		// count from 5 to 0 and then start the race

		// instantiate three runner threads
		new Runner(counter, "Carl");
		new Runner(counter, "Joe");
		new Runner(counter, "Jack");

		System.out.println("Starting the countdown ");
		long countVal = counter.getCount();
		while (countVal > 0) {
			Thread.sleep(1000); // 1000 milliseconds = 1 second
			System.out.println(countVal);
			if (countVal == 1) {
				// once counter.countDown(); in the next statement is called,
				// Count down will reach zero; so shout "Start"
				System.out.println("Start");
			}
			counter.countDown(); // count down by 1 for each second
			countVal = counter.getCount();
		}
	}
}
