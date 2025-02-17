package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor {
	
	private Position pos;
	
	
	
	public ExitDoor(Position pos) {
		this.pos = pos;
	}
	
	
	
	public Position getPosition() {			//getter exitDoor	
		return pos;
	}
	
	public void setPosition(Position pos) {	//setter exitDoor
		this.pos = pos;
	}
	
	public String getIcon() {
		return Messages.EXIT_DOOR;
	}
	
	/**
	 *  Implements the automatic update	
	 */
	public void update() {
		//TODO fill your code
		//No hace nada
	}
}
