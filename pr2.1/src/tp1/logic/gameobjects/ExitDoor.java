package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor extends GameObject {
	
	private static final String ICON = Messages.EXIT_DOOR;
	
	
	
	public ExitDoor(Game game, Position pos) {
		super(game, pos);
	}
	
	public Position getPosition() {			//getter exitDoor	
		return pos;
	}
	
	public void setPosition(Position pos) {	//setter exitDoor
		this.pos = pos;
	}
	
	public String getIcon() {
		return ICON;
	}
	
	/**
	 *  Implements the automatic update	
	 */
	public void update() {
		//No hace nada
	}

	public boolean isExit() {
		return true;
	}
}
