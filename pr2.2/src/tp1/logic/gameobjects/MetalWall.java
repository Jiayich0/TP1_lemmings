package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class MetalWall extends GameObject{

	private static final String ICON = Messages.METALWALL;
	
	
	public MetalWall(Game game, Position pos) {
		super(game, pos);
	}
	
	public Position getPosition() {													// Getter 'pos'	
		return pos;
	}
	
	public String getIcon() {														// Getter 'ICON'
		return ICON;
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
}
