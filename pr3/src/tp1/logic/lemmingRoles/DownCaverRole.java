package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.*;
import tp1.view.Messages;

public class DownCaverRole extends AbstractRole {
	
	private static final String NAME = Messages.DOWNCAVER_ROL_NAME;
	private static final String SHORTCUT = Messages.DOWNCAVER_ROL_SYMBOL;
	private static final String HELP = Messages.DOWNCAVER_ROL_HELP;
	
	private static final String ICON_DOWNCAVER = Messages.LEMMING_DOWN_CAVER;
	
	private boolean hasCaved = false;
	
	
	public DownCaverRole() {
		super(NAME, HELP, SHORTCUT);
	}
	
	public void play(Lemming lemming) {												// Play de DownCaver
			
		if(hasCaved) {																// Se hace Interactions antes de play
			lemming.fall();															// dando valor a 'hasCaved'
			lemming.setFallForce(0);
			this.hasCaved = false;
		}
		else {
			lemming.disableRole();	
			lemming.update();
		}
	}
	
	public String getIcon(Lemming lemming) { 
		return  ICON_DOWNCAVER;
	}
	
	public LemmingRole createInstance() {											// Devuelve la clase
		return this;
	}
	
	//interact
	public boolean interactWith(Wall wall, Lemming lemming) {						// Interacción entre DCRole y Wall
		boolean interactua = lemming.isOver(wall);									// Llamado por Lemming
		if(interactua) {
			hasCaved = true;
			wall.kill();
		}
		return interactua;
	}
}
