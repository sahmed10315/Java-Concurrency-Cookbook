package concurrency.javaframework.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class MixedDoubleTennisGame extends Thread {
	public void run() {
		System.out.println("All four players ready, game starts \n Love all...");
	}
}

class Player extends Thread {
	CyclicBarrier waitPoint;

	public Player(CyclicBarrier barrier, String name) {
		this.setName(name);
		waitPoint = barrier;
		this.start();
	}

	public void run() {
		System.out.println("Player " + getName() + " is ready ");
		try {
			waitPoint.await(); // await for all four players to arrive
		} catch (InterruptedException exception) {
			System.out.println("An exception occurred while waiting... " + exception);
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}

public class CyclicBarrierTest {
	public static void main(String[] args) {
		// a mixed-double tennis game requires four players; so wait for four
		// players 
		System.out.println("Reserving tennis court \n As soon as four players arrive, game will start");
		CyclicBarrier barrier = new CyclicBarrier(4, new MixedDoubleTennisGame());
		new Player(barrier, "G I Joe");
		new Player(barrier, "Dora");
		new Player(barrier, "Tintin");
		new Player(barrier, "Barbie");
	}
}