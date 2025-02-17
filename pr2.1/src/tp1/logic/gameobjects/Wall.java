package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Wall extends GameObject{
	
	private static final String ICON = Messages.WALL;
	
	
	
	public Wall(Game game, Position pos) {
		super(game, pos);
	}
	
	public Position getPosition() {				//getter Wall
		return pos;
	}
	
	public void setPostion(Position pos) {		//setter Wall
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
	
	public boolean isSolid() {
		return true;
	}
}
