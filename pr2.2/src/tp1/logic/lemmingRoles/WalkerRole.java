package tp1.logic.lemmingRoles;


import tp1.logic.Direction;
import tp1.logic.gameobjects.Lemming;
import tp1.view.Messages;

public class WalkerRole extends AbstractRole {
	
	private static final String NAME = Messages.WALKER_ROL_NAME;
	private static final String SHORTCUT = Messages.WALKER_ROL_SYMBOL;
	private static final String HELP = Messages.WALKER_ROL_HELP;
	
	private static final String ICON_RIGHT = Messages.LEMMING_RIGHT;
	private static final String ICON_LEFT = Messages.LEMMING_LEFT;
	
	public WalkerRole() {
		super(NAME, HELP, SHORTCUT);
	}
	
	public void play(Lemming lemming) {												// Play de Walker
		lemming.caminaOCae();
	}
	
	public String getIcon(Lemming lemming) {
		if(lemming.getDirection() == Direction.RIGHT) {
			return ICON_RIGHT;
		}
		else {
			return ICON_LEFT;
		}

	}

	public LemmingRole createInstance() {											// Devuelve la clase
		return this;
	}
}
