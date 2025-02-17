package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class Wall extends GameObject{
	
	private static final String ICON = Messages.WALL;
	private static final String NAME = Messages.WALL_NAME;
	private static final String SHORTCUT = Messages.WALL_SHORTCUT;

	
	
	public Wall(GameWorld game, Position pos) {
		super(game, pos);
	}
	
	public Wall() {																// Constructor para GameObjectFactory
		super(null, null);
	}
	
	public Position getPosition() {													// Getter 'pos'
		return pos;
	}
	
	public String getIcon() {														// Getter 'ICON'
		return ICON;
	}
	
	public String getName() {														// Getter 'NAME'
		return NAME;
	}
	
	public String getShortcut() {													// Getter 'SHORTCUT'
		return SHORTCUT;
	}
	
	public GameObject createNew(GameWorld game, Position pos) {
		return new Wall(game, pos);
	}
	
	public GameObject copy() {
		return new Wall(this.game, this.pos);
	}
	
	/**
	 *  Implements the automatic update	
	 */
	public void update() {
		//No hace nada
	}
	
	public boolean isSolid() {														// Actualiza 'isSolid' a True
		return true;
	}
	
	// interact 
	public boolean receiveInteraction(GameItem other){								// Interactúa con Lemming (DCRole)
		 return other.interactWith(this);
	}
	
	public GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException {
		return (Wall)super.parse(line, game);
	}
}
