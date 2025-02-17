package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor extends GameObject {
	
	private static final String ICON = Messages.EXIT_DOOR;
	private static final String NAME = Messages.EXIT_DOOR_NAME;
	private static final String SHORTCUT = Messages.EXIT_DOOR_SHORTCUT;
	
	
	
	public ExitDoor(GameWorld game, Position pos) {
		super(game, pos);
	}
	
	public ExitDoor() {																// Constructor para GameObjectFactory
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
		return new ExitDoor(game, pos);
	}
	
	public GameObject copy() {
		return new ExitDoor(this.game, this.pos);
	}
	
	/**
	 *  Implements the automatic update	
	 */
	public void update() {
		//No hace nada
	}

	public boolean isExit() {														// Actualiza 'isExit' a True
		return true;
	}
	
	public GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException {
		return (ExitDoor)super.parse(line, game);
	}
}
