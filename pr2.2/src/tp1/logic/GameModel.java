package tp1.logic;

import tp1.logic.lemmingRoles.LemmingRole;

public interface GameModel {														// Para Controller
	
	public boolean isFinished();
	public void update();
	public void reset();
	public void exit();
	public boolean setRole(LemmingRole role, Position pos);
}
