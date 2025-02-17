package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;

public abstract class GameObject implements GameItem{

	protected Position pos;
	protected boolean isAlive;
	protected GameWorld game;
	
	public GameObject(GameWorld game, Position pos) {
		this.isAlive = true;
		this.pos = pos;
		this.game = game;
	}
	
	// interact
	public boolean receiveInteraction(GameItem other) { return false; }				// Default Todo en False
	
	public boolean interactWith(Lemming lemming) { return false; }
	public boolean interactWith(Wall wall) { return false; }
	public boolean interactWith(ExitDoor door) { return false; }
	public boolean interactWith(MetalWall metalWall) { return false; }
	
	// pr2.1
	public boolean isSolid() {														// Default False - Wall & MetalWall True
		return false;
	}
	
	public boolean isAlive() {														// Estado de 'isAlive'
		return isAlive;
	}
	
	public boolean isExit() {														// Default False - ExitDoor True
		return false;
	}
	
	public boolean isInPosition(Position p) {										// 'pos' == 'p'
		return pos.equals(p);
	}
	
	public abstract void update();													// Solo Lemming tiene update() (Actualmente)
	
	public abstract String getIcon();												// Getter 'icon' de cada Object
	
	public boolean setRole(LemmingRole role) {										// Default False - Lemming lo implementa
		return false;
	}
	
	public void kill() {															// Estado de 'isAlive' a False
		isAlive = false;
	}
}
