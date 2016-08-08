package threads.simple;

/* A thread can have a priority from 0-9, along with a name, which can be assigned */
public class ThreadPriorities {

	public static void main(String [] args) {
		Thread newThread = new Thread();
		
		newThread.setName("Saad");
		newThread.setPriority(9);
		System.out.println(newThread);
		
		System.out.println("Minimum priority of a thread: " + Thread.MIN_PRIORITY);
		System.out.println("Normal priority of a thread: " + Thread.NORM_PRIORITY);
		System.out.println("Maximum priority of a thread: " + Thread.MAX_PRIORITY);
		
	}
}
