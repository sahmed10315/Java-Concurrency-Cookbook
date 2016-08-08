package threads.simple.joins;

/* A simple example of join() where a thread waits for another thread to finish, by calling its join method, before continuing its execution */

public class CountDown extends Thread {
	
	String [] countDown = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
	
	public void run() {
		for (int i = 9; i >= 0; i--) {
			try {
				System.out.println(countDown[i]);
				Thread.sleep(1000);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
	
	public static void main(String [] args) {
		CountDown countDown = new CountDown();
		
		System.out.println("Starting ten second count down... ");
		
		countDown.start();
		
		try {
			countDown.join();
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
		
		System.out.println("Finished");
	}

}
