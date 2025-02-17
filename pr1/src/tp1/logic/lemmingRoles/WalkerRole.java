package tp1.logic.lemmingRoles;


import tp1.logic.Direction;
import tp1.logic.gameobjects.Lemming;
import tp1.view.Messages;

public class WalkerRole {
	
	private boolean enCaida = false;
	
	
	
	public void play(Lemming lemming) {						//comportamiento de Walker Lemming
		lemming.caminaOCae();
	}
	
	
	public String getIcon(Lemming lemming) {				//settea los iconos 
		if(lemming.getDirection() == Direction.RIGHT) {
			return Messages.LEMMING_RIGHT;
		}
		else {
			return Messages.LEMMING_LEFT;
		}

	}
}
