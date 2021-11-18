package donkeykong;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Main class. Processes user input and calls GameOps to run the game.
 * @author Grace Bian
 */
public class DonkeyKong {	
	/**
	 * Main method to handle user input and start the game
	 * @param args
	 */
	public static void main(String[] args) {
		// Define the min and max coordinates on a 7*7 board (a reasonable size to get ideal performance)
		int min = 0;
		int max = 6;
		
		// Create a new scanner to get console input
	    Scanner scanner = new Scanner(System.in);
	    // Null string to store the user input
	    String option = null;
		
	    // Display the welcome message
		System.out.println("       Welcome to the Donkey Kong Game!");
		
		// A set of allowed message
		Set<String> options = new HashSet<String>();
		options.add("0");
		options.add("1");
		
		// Infinite loop that waits for user input
		while (true) {
			System.out.print("       ----------Donkey Kong MAIN MENU--------\n"
	    			+ "       1. Start \n"
	    			+ "       0. Exit \n");
			
			// Read user input
	    	option = scanner.next();
	    	
	    	// If invalid input has been entered, display the error message and ask for new input
	    	if (!options.contains(option)) {
	    		System.out.println("Invalid input. Try again.");
	    		continue;
	    	}
	    	
	    	// If option exit selected, exit the program
	    	if (option.equals("0")) {
		    	System.out.println("You selected 0 to exit.");
		    	System.out.println("Thank You for Playing Donkey Kong! See You Next Time!");
		    	System.exit(0);
		    }
	    	
	    	// If option start selected, start the game
	    	if (option.equals("1")) {
	    		// Option 1 selected, break the loop to start the game
	    		break;  
	    	}
		}
		
		// Close the scanner
		scanner.close();
		
		// Create GameOps instance and run the game
		GameOps go = new GameOps();
		go.runGame(min, max);
	}
}
