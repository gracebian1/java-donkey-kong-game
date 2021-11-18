package donkeykong;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * GameOps class to run the game. Includes several key methods to generate random coordinates, e.g., check if Mario got the hammer,
 * check if Mario hit either gorilla, cask, or fireball, as well as check if Mario hit the cask or fireball using hammer, etc.
 * 
 * Some preset features: The positions of gorilla and princess are always next to each other and with the same y-coordinate;
 * Positions for characters and obstacles as well as hammers are randomly generated;
 * Mario will be dead if he hits either gorilla, or cask or fireball (without using hammer);
 * Mario could use hammer to hit cask and fireball;
 * Three hammers has been set to the game, a reasonable size to get ideal performance;
 * Each hammer could be used for 5 rounds;
 * When certain cask drops into the oil, it will change into fireball;
 * When Mario got a hammer, fireball changed color;
 * Gorilla throws a new cask every 3 rounds; 
 * Cask and fireball move 1 step for each round; 
 * Mario can move 1 or 2 steps each round; 
 * To simulate the jump and climb moves, Mario is considered jumping if x-coordinate moves two steps; 
 * Mario is considered climbing if y-coordinate moves two steps.
 * 
 * @author Grace Bian
 */
public class GameOps {
	/**
	 * Generate a random number between min and max (inclusive)
	 * @param min
	 * @param max
	 * @return
	 */
	public int generateRandom(int min, int max) {
		Random rand = new Random();
		// Generates random integer within range from min to max (inclusive)
		int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	/**
	 * Pause execution with sleep between each round (ms)
	 * @param ms
	 */
	public void wait(int ms) {
	    try {
	        Thread.sleep(ms);
	    } catch(InterruptedException ex) {
	        Thread.currentThread().interrupt();
	    }
	}
	
	/**
	 * Check if Mario and hammer are at the same position, indicating Mario got the hammer
	 * @param hammers
	 * @param mario
	 * @return
	 */
	public boolean checkHammer(Hammer[] hammers, Mario mario) {
		// Loop through the hammers array to check if Mario is at the same position as the hammer
		for (Hammer h : hammers) {
			// If Mario is at the hammer position and the hammer is not used yet
			if (mario.getX() == h.getX() && mario.getY() == h.getY() && !h.isUsed()) {
				// Reset hammer coordinate (Mario can get each hammer only once)
				h.setUsed(true);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check if Mario is at the same position as either gorilla, fireball, or cask, indicating Mario hit one of these;
	 * And if cask is at the same position as oil, it will turn into fireball.
	 * @param gorilla
	 * @param fireball
	 * @param casks
	 * @param oil
	 * @param mario
	 * @return
	 */
	public boolean checkHit(Gorilla gorilla, Fireball fireball, List<Cask> casks, Oil oil, Mario mario) {
		// If Mario is at the same coordinates as gorilla
		if (mario.getX() == gorilla.getX() && mario.getY() == gorilla.getY()) {
			System.out.println("Mario hit gorilla!");
			System.out.println("The coordinates is: (" + mario.getX() + ", " + mario.getY() + ")");
			return true;
		} 
		// If Mario is at the same coordinates as fireball
		if (mario.getX() == fireball.getX() && mario.getY() == fireball.getY()) {
			System.out.println("Mario hit fireball!");
			System.out.println("The coordinates is: (" + mario.getX() + ", " + mario.getY() + ")");
			return true;
		}
		
		// Check cask position
		for (Cask c : casks) {
			// If cask is at the same coordinate as the oil, cask will turn into fireball
			if (c.getX() == oil.getX() && c.getY() == oil.getY()) {
				System.out.println("Cask turned into fireball!");
			}
			// Check if Mario hits cask
			if (mario.getX() == c.getX() && mario.getY() == c.getY()) {
				System.out.println("Mario hit cask!");
				System.out.println("The coordinates is: (" + mario.getX() + ", " + mario.getY() + ")");
				return true;
			}
		}
		// Return false if Mario hits none of these obstacles
		return false;
	}
	
	/**
	 * Mario randomly moves one or two steps, forward or backward. Also check for border condition. 
	 * If Mario moves two steps horizontally, it indicates jumping;
	 * if moves two steps vertically, it indicates climbing up or down a ladder.
	 * @param mario
	 * @param min
	 * @param max
	 */
	public void moveMario(Mario mario, int min, int max) {
		// Set Mario movement steps. Mario can move 1 or 2 steps, either forward or backward.
		// These two variables represent the relative change of steps.
		int marioMoveX;
		int marioMoveY;
		// Integer array includes four possibilities of steps
		int[] movement = {-2, -1, 1, 2};
		
		// If Mario is at the border (for x-coordicate), Mario steps back for 1 step
		if (mario.getX() >= max - 1) {
			marioMoveX = -1;
		} else if (mario.getX() <= min + 1) {
			marioMoveX = 1;
		} else {
			// If not at the border, Mario randomly goes one or two steps, either forward or backward
			int index = generateRandom(0, 3);
			marioMoveX = movement[index];
		}
		
		// If Mario is at the border (for y-coordinate), Mario steps back for 1 step
		if (mario.getY() >= max - 1) {
			marioMoveY = -1;
		} else if (mario.getY() <= min + 1) {
			marioMoveY = 1;
		} else {
			// If not at the border, Mario randomly goes one or two steps, either forward or backward
			int index = generateRandom(0, 3);
			marioMoveY = movement[index];
		}
		
		// If Mario moves horizontally 2 steps, it indicates jumping
		if (marioMoveX == 2 || marioMoveX == -2) {
			// Add current position to the changing coordinates, get the new position
			mario.jump(mario.getX() + marioMoveX, mario.getY() + marioMoveY);
		} 
		// If Mario moves vertically 2 steps, it indicates climbing up or down a ladder
		else if (marioMoveY == 2 || marioMoveY == -2) {
			// Add current position to changing coordinates, the result is the new position
			mario.climbLadder(mario.getX() + marioMoveX, mario.getY() + marioMoveY);
		} 
		// else Mario moves
		else {
			mario.moveTo(mario.getX() + marioMoveX, mario.getY() + marioMoveY);
		}
	}
	
	/**
	 * Cask move conditions.
	 * @param casks
	 * @param min
	 * @param max
	 */
	public void moveCasks(List<Cask> casks, int min, int max) {
		// Move all the casks
		for (int i = 0; i < casks.size(); i++) {
			Cask c = casks.get(i);
			// Set cask movement steps. Cask can move one step, either forward or backward
			int caskMoveX;
			int caskMoveY;
			int[] caskMovement = {-1, 1};
			// In case cask is on the border on x-coordinate, step back
			if (c.getX() >= max) {
				caskMoveX = -1;
			} else if (c.getX() <= min) {
				caskMoveX = 1;
			} else {
				// Randomly generate one cask move on x-coordinate
				int index = generateRandom(0, 1);
				caskMoveX = caskMovement[index];
			}
			// In case cask is on the border on y-coordinate, step back
			if (c.getY() >= max) {
				caskMoveY = -1;
			} else if (c.getY() <= min) {
				caskMoveY = 1;
			} else {
				// Randomly generate cask move on y-coordinate
				int index = generateRandom(0, 1);
				caskMoveY = caskMovement[index];
			}
			// Display cask position message
			System.out.println("Cask " + i + ":");
			c.moveTo(c.getX() + caskMoveX, c.getY() + caskMoveY);	
		}
	}
	
	/**
	 * Fireball move conditions. 
	 * @param fireball
	 * @param fireballChangeColor
	 * @param min
	 * @param max
	 */
	public void moveFireball(Fireball fireball, boolean fireballChangeColor, int min, int max) {
		// Set fireball movement steps. Fireball can move one step, either forward or backward
		int fireballMoveX;
		int fireballMoveY;
		int[] fireballMovement = {-1, 1};
		// In case fireball is on the border on x-coordinate, step back
		if (fireball.getX() >= max) {
			fireballMoveX = -1;
		} else if (fireball.getX() <= min) {
			fireballMoveX = 1;
		} else {
			// Randomly generate fireball move on x-coordinate
			int index = generateRandom(0, 1);
			fireballMoveX = fireballMovement[index];
		}
		// In case fireball is on the border on y-coordinate, step back
		if (fireball.getY() >= max) {
			fireballMoveY = -1;
		} else if (fireball.getY() <= min) {
			fireballMoveY = 1;
		} else {
			// Randomly generate fireball move on y-coordinate
			int index = generateRandom(0, 1);
			fireballMoveY = fireballMovement[index];
		}
		// Fireball moves to target coordinate, with colorChange parameter
		fireball.moveTo(fireball.getX() + fireballMoveX, fireball.getY() + fireballMoveY, fireballChangeColor);
	}
	
	/**
	 * Run the game.
	 * @param min
	 * @param max
	 */
	public void runGame(int min, int max) {
		// Generate random position for mario
		int marioX = generateRandom(min, max);
		int marioY = generateRandom(min, max);
		
		// Generate random position for gorilla
		int gorillaX = generateRandom(min, max);
		int gorillaY = generateRandom(min, max);
		
		// Princess's x-coordinate is always next to the gorilla
		int princessX;
		if (gorillaX == max) {
			princessX = gorillaX - 1;
		} else {
			princessX = gorillaX + 1;
		}
		// Princess always has the same y-coordinate as the gorilla
		int princessY = gorillaY;
		
		// Generate random position for cask
		int caskX = generateRandom(min, max);
		int caskY = generateRandom(min, max);
		
		// Generate random position for fireball
		int fireballX = generateRandom(min, max);
		int fireballY = generateRandom(min, max);
		
		// Oil position is fixed, at the bottom left corner 
		int oilX = min;
		int oilY = max;
		
		// Create 3 hammers using array
		int numOfHammers = 3;
		Hammer[] hammers = new Hammer[numOfHammers];
		
		// Create hammer instances in array
		for (int i = 0; i < numOfHammers; i++) {
			//  Generate random position for hammer
			int hammerX = generateRandom(min, max);
			int hammerY = generateRandom(min, max);
			// New hammer instance created
			Hammer h = new Hammer(hammerX, hammerY, false);
			// Add to array
			hammers[i] = h;
		}
		
		// Characters initialized, mario, princess and gorilla
		Mario mario = new Mario(marioX,marioY,false);
		Princess princess = new Princess(princessX, princessY, false);
		Gorilla gorilla = new Gorilla(gorillaX, gorillaY);
		
		// Display characters' coordinates
		System.out.println("Mario is located at: (" + marioX + ", " + marioY + ")");
		System.out.println("Princess is located at: (" + princessX + ", " + princessY + ")");
		System.out.println("Gorilla is located at: (" + gorillaX + ", " + gorillaY + ")");
		
		// Cask, fireball and oil initialized
		Cask cask = new Cask(caskX, caskY);
		Fireball fireball = new Fireball(fireballX, fireballY);
		Oil oil = new Oil(oilX, oilY);
		
		// Cask arraylist to store casks 
		List<Cask> casks = new ArrayList<>();
		casks.add(cask);
		
		// Fireball does not change color initially, it changes color when Mario gets the hammer in hand
		boolean fireballChangeColor = false;
		
		// Initiate round count as 0
		int round = 0;
		
		// Loop to run the game
		while (true) {
			// Increase round number for each new round
			round++;
			System.out.println("========Round: " + round + "========");
			
			// If Mario has the hammer, increase the hammer timer by 1
			if (mario.hasHammer()) {
				mario.setHammerTimer(mario.getHammerTimer() + 1);
			}
			
			// If Mario keeps the hammer for more than 5 rounds (inclusive), reset hammer and the color of fireball
			if (mario.getHammerTimer() >= 5) {
				mario.setHammerTimer(0);
				mario.setHasHammer(false);
				fireballChangeColor = false;
			} 
			
			// If Mario is at the same coordinate as the princess, the player won
			if (mario.getX() == princess.getX() && mario.getY() == princess.getY()) {
				princess.setWasSaved(true);
				System.out.println("You won!");
				break;
			}
			
			// Check if Mario got a hammer, if Mario and a hammer is at the same position
			if (checkHammer(hammers, mario)) {
				System.out.println("Mario got hammer!");
				// Set Mario hammer to true
				mario.setHasHammer(true);
				// Fireball changes color
				fireballChangeColor = true;
			}
			
			// When Mario does not got hammer to hit the cask or fireball, Mario is dead 
			if (checkHit(gorilla, fireball, casks, oil, mario)) {
				if (!mario.hasHammer()) {
					mario.setDead(true);
					break;
				} else {
					// When Mario got the hammer, he is still alive
					System.out.println("Mario is not dead, because he got hammer!");
				}
			}
			
			// Gorilla throws a new cask every 3 rounds 
			if (round % 3 == 0) {
				gorilla.throwCask();
				// Generate random location for cask
				int newCaskX = generateRandom(min, max);
				int newCaskY = generateRandom(min, max);
				// Add new cask to cask arraylist
				Cask c = new Cask(newCaskX, newCaskY);
				casks.add(c);
			}
			
			// Sleep half second for the next operation
			wait(1000);
			// Call move methods on Mario, cask and fireball
			moveMario(mario, min, max);			
			moveCasks(casks, min, max);			
			moveFireball(fireball, fireballChangeColor, min, max);
		}
	}
}
