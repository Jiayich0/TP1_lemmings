package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;

public class Wall {
	
	private Position pos;
	
	
	
	public Wall(Position pos) {
		this.pos = pos;
	}
	
	
	
	public Position getPosition() {				//getter Wall
		return pos;
	}
	
	public void setPostion(Position pos) {		//setter Wall
		this.pos = pos;
	}
	
	public String getIcon() {
		return Messages.WALL;
	}
	
	/**
	 *  Implements the automatic update	
	 */
	public void update() {
		//TODO fill your code
		//No hace nada
	}
}
