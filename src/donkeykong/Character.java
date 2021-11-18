package donkeykong;
/**
 * Character abstract class that defines coordinates of character. Includes inheritance and overriding. 
 * @author Grace Bian
 */
abstract class Character {	
	int x, y;
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	// Method to be overridden by subclasses
	public void moveTo(int newX, int newY) {
		System.out.println("Character moved to coordinate: (" + newX + ", " + newY + ")");
	}
}

/**
 * Inheritance: Mario class extends Character class. Overriding superclass's method.
 */
class Mario extends Character {
	// Boolean variable: if Mario has hammer
	private boolean hasHammer;
	// Boolean variable initially set to false
	private boolean isDead = false;
	// Integer variable: time for being able to keep the hammer in hands, counted by number of rounds
	private int hammerTimer = 0;

	public Mario(int x, int y, boolean hasHammer) {
		super();
		this.x = x;
		this.y = y;
		this.hasHammer = hasHammer;
	}
    // Getter for hammer timer
	public int getHammerTimer() {
		return hammerTimer;
	}
    // Setter for hammer timer	
	public void setHammerTimer(int hammerTimer) {
		this.hammerTimer = hammerTimer;
	}

	public boolean isDead() {
		return isDead;
	}

	// Setter for isDead variable
	public void setDead(boolean isDead) {
		// If Mario is dead, display message
		if (isDead) {
			System.out.println("Mario is dead. Game failed...");
		}
		this.isDead = isDead;
	}

	// Getter for Mario hasHammer
	public boolean hasHammer() {
		return hasHammer;
	}

	// Setter for Mario hasHammer
	public void setHasHammer(boolean hasHammer) {
		this.hasHammer = hasHammer;
	}
	
	// Overrides superclass's moveTo method. 
	public void moveTo(int newX, int newY) {
		this.x = newX;
		this.y = newY;
		System.out.println("Mario moved to coordinate: (" + newX + ", " + newY + ")");
	}
	
	// Mario jumps to coordinate
	public void jump(int newX, int newY) {
		this.x = newX;
		this.y = newY;
		System.out.println("Mario jumped to coordinate: (" + newX + ", " + newY + ")");
	}
	
	// Mario climbs to coordinate
	public void climbLadder(int newX, int newY) {
		this.x = newX;
		this.y = newY;
		System.out.println("Mario climbed ladder, to coordinate: (" + newX + ", " + newY + ")");
	}
}

/**
 * Inheritance: Princess class extends Character class. Overriding superclass's method.
 */
class Princess extends Character {
	// Subclass boolean variable
	private boolean wasSaved;
	// Princess class constructor
	public Princess(int x, int y, boolean wasSaved) {
		super();
		this.x = x;
		this.y = y;
		this.wasSaved = wasSaved;
	}
	
	public boolean wasSaved() {
		return wasSaved;
	}
    // Setter method 
	public void setWasSaved(boolean wasSaved) {
		System.out.println("Princess was saved!");
		this.wasSaved = wasSaved;
	}
	// Overrides super class's moveTo method. 
	public void moveTo(int newX, int newY) {
		this.x = newX;
		this.y = newY;
		System.out.println("Princess has been moved to coordinate: X: " + newX + "; Y: " + newY);
	}
}

/**
 *  Inheritance: Gorilla class extends Character class. Overriding superclass's method.
 */
class Gorilla extends Character {
	// Constructor for gorilla class
	public Gorilla(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	// Overrides super class's moveTo method. 
	public void moveTo(int newX, int newY) {
		this.x = newX;
		this.y = newY;
		System.out.println("Gorilla moved to coordinate: X: " + newX + "; Y: " + newY);
	}
	// Throwcask method for gorilla class
	public void throwCask() {
		System.out.println("Gorilla threw cask!");
	}
}