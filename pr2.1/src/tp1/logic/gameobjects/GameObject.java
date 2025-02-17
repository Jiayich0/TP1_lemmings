package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class GameObject {

	protected Position pos;
	protected boolean isAlive;
	protected GameWorld game;
	
	public GameObject(GameWorld game, Position pos) {
		this.isAlive = true;
		this.pos = pos;
		this.game = game;
	}
	
	public boolean isInPosition(Position p) {
		// TODO fill your code here, it should depends on the status of the object
		return pos.equals(p);
	}
 	
	public boolean isAlive() {
		return isAlive;
	}
	
	// TODO implement and decide, Which one is abstract?
	public boolean isSolid() {
		return false;
	}
	
	public boolean isExit() {
		return false;
	}
	
	public abstract void update();
	
	public abstract String getIcon();

}
