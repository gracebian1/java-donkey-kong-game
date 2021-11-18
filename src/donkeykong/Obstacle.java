package donkeykong;
/**
 * Obstacle class that defines the location of obstacles.
 * Includes inheritance, overriding and overloading. 
 * @author Grace Bian
 */
class Obstacle {
	int x, y;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
    // Super class method to be overridden and overloaded. 
	void moveTo(int newX, int newY) {
		this.x = newX;
		this.y = newY;
	}
}

/**
 * Cask class that extends Obstacle superclass and defines the location of casks and movement of casks.
 * Inheritance and overriding.
 */
class Cask extends Obstacle {
	public Cask(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	// Override the moveTo method from superclass
	public void moveTo(int newX, int newY) {
		this.x = newX;
		this.y = newY;
		System.out.println("Cask moved to coordinate: (" + newX + ", " + newY + ")");
	}
}

/**
 * Fireball class that extends Obstacle superclass and defines the location of fireballs and movement of fireballs.
 * Inheritance and overloading. 
 */
class Fireball extends Obstacle {
	public Fireball(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	// Overload the moveTo method from superclass, with additional changeColor argument
	// If Mario gets the hammer, fireball changes color
	public void moveTo(int newX, int newY, boolean changeColor) {
		this.x = newX;
		this.y = newY;
		if (changeColor) {
			System.out.println("Mario got the hammer, fireball changed to blue!");
		}
		System.out.println("Fireball moved to coordinate: (" + newX + ", " + newY + ")");
	}
}

/**
 * Oil class that defines the location of oil.
 */
class Oil{
	private int x;
	private int y;
	
	public Oil(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
