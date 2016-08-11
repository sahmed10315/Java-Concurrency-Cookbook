package threads.examples.waitnotify.dicegame;

import java.util.Random;
 
public class Dice { 
	private static String turn = null;

	synchronized public static String getTurn() {
		return turn;
	}

	synchronized public static void setTurn(String player) {
		turn = player;
	}
 
	public static void setWhoStarts(String name) {
		turn = name;
	}
 
	private Dice() {
	}
 
	private static Random random = new Random();

	// random.nextInt(6) gives values from 0 to 5, so add 1 to result in roll()
	public static int roll() {
		return random.nextInt(6) + 1;
	}
}
