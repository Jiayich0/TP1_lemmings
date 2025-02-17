package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class MetalWall extends GameObject{

	private static final String ICON = Messages.METALWALL;
	private static final String NAME = Messages.METAL_WALL_NAME;
	private static final String SHORTCUT = Messages.METAL_WALL_SHORTCUT;
	
	
	
	public MetalWall(GameWorld game, Position pos) {
		super(game, pos);
	}
	
	public MetalWall() {																// Constructor para GameObjectFactory
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
		return new MetalWall(game, pos);
	}
	
	public GameObject copy() {
		return new MetalWall(this.game, this.pos);
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
	
	public GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException {
		return (MetalWall)super.parse(line, game);
	}
}
