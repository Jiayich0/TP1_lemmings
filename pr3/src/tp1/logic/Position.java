package tp1.logic;

/**
 * 
 * Immutable class to encapsulate and manipulate positions in the game board
 * 
 */
public class Position {

	private int row;
	private int col;

	
	
	public Position (int x, int y) {
		this.row = x;
		this.col = y;
	}
	
	
	public Position move(Direction dir) {
		return new Position(this.row + dir.getY(), this.col + dir.getX());			// Position modificado pero Direction no
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public boolean equals(Object obj) {												// Excepción getClass()
		if (this == obj)
			return true;
		if (obj == null)
	        return false;
		if (getClass() != obj.getClass())
	        return false;
		Position other = (Position) obj;
		return this.row == other.row && this.col == other.col;
	}
}
