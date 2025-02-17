package tp1.logic;

/**
 * Represents the allowed directions in the game
 *
 */
public enum Direction {
	LEFT(-1,0), RIGHT(1,0), DOWN(0,1), UP(0,-1), NONE(0,0);
	
	private int x;
	private int y;
	
	private Direction(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Direction opuesta() {
		Direction dirOpuesto = NONE;
		
		switch(this) {
		case LEFT: dirOpuesto = RIGHT; break;
		case RIGHT: dirOpuesto = LEFT; break;
		case UP: dirOpuesto = DOWN; break;
		case DOWN: dirOpuesto = UP; break;
		default:dirOpuesto = NONE; break;
		}
		return dirOpuesto;
	}
	
	public static Direction stringToDir(String str) {
		if (str.equals("RIGHT"))
			return RIGHT;
		else if (str.equals("LEFT"))
			return LEFT;
		else if (str.equals("UP"))
			return UP;
		else if (str.equals("DOWN"))
			return DOWN;
		else
			return null;
	}
}
