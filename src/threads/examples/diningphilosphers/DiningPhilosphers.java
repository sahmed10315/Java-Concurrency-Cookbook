package threads.examples.diningphilosphers;

public class DiningPhilosphers {

	private Object[] forks;
	private Philospher[] philosphers;

	DiningPhilosphers(int num) {
		forks = new Object[num];
		philosphers = new Philospher[num];
		for (int i = 0; i < num; ++i) {
			forks[i] = new Object();
			philosphers[i] = new Philospher(i, i, (i + 1) % num);
		}
	}

	public void startEating() throws InterruptedException {
		for (int i = 0; i < philosphers.length; i++)
			philosphers[i].start();
		philosphers[0].join();
	}

	class Philospher extends Thread {
		private int id;
		private int fork1;
		private int fork2;

		Philospher(int id, int fork1, int fork2) {
			this.id = id;
			this.fork1 = fork1;
			this.fork2 = fork2;
		}

		public void run() {
			status("Ready to eat using forks " + fork1 + " and " + fork2);

			while (true) {
				status("Picking up fork " + fork1);
				synchronized (forks[fork1]) {
					status("Picking up fork " + fork2);
					synchronized (forks[fork2]) {
						status("Eating");
					}
				}
			}
		}

		private void status(String msg) {
			System.out.println("Philospher " + id + ": " + msg);
		}
	}

	public static void main(String[] args) {
		try {
			DiningPhilosphers d = new DiningPhilosphers(5);
			d.startEating();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
}
