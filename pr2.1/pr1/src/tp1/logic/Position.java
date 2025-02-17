package tp1.logic;

/**
 * 
 * Immutable class to encapsulate and manipulate positions in the game board
 * 
 */
public class Position {

	private int col;
	private int row;

	//TODO fill your code
	public Position (int x, int y) {
		this.col = x;
		this.row = y;
	}
	
	public Position move(Direction dir) {
		return new Position(this.col + dir.getX(), this.row + dir.getY());
	}
	
	
	public int getCol() {
		return col;
	}
	
	public int getRow() {
		return row;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
	        return false;
		if (getClass() != obj.getClass())
	        return false;
		Position other = (Position) obj;
		return this.col == other.col && this.row == other.row;
	}
}
