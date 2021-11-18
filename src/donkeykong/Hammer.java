package donkeykong;

/**
 * Hammer class defines hammer variables, getters and setters.
 * @author Grace Bian
 */
public class Hammer {
	private boolean isUsed;
	private int x;
	private int y;
	// Constructor 
	public Hammer(int x, int y, boolean isUsed) {
		super();
		this.x = x;
		this.y = y;
		this.isUsed = isUsed;
	}
    // Getters and setters
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

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
	
}
