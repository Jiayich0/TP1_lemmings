package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.Lemming;
import tp1.view.Messages;

public class ParachuterRole extends AbstractRole {
	
	private static final String NAME = Messages.PARACHUTER_ROL_NAME;
	private static final String SHORTCUT = Messages.PARACHUTER_ROL_SYMBOL;
	private static final String HELP = Messages.PARACHUTER_ROL_HELP;
	
	private static final String ICON_PARACHUTER = Messages.LEMMING_PARACHUTE;
	
	
	public ParachuterRole() {
		super(NAME, HELP, SHORTCUT);
	}
	
	public void play(Lemming lemming) {												// Play de Parachuter
		
		lemming.setFallForce(0);
		if (lemming.isInAir()) {
			lemming.fall();
		} 
		else {
			lemming.land();					// creo que es omitible
			lemming.disableRole();
			lemming.update();
		}
	}
	
	public String getIcon(Lemming lemming) { 
		return  ICON_PARACHUTER;
	}
	
	public LemmingRole createInstance() {											// Devuelve la clase
		return this;
	}
}
