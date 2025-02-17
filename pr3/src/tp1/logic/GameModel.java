package tp1.logic;

import tp1.exceptions.GameLoadException;
import tp1.exceptions.OffBoardException;
import tp1.exceptions.RoleParseException;
import tp1.logic.lemmingRoles.LemmingRole;

public interface GameModel {														// Para Controller
	
	public boolean isFinished();
	public void update();
	public void reset();
	public void exit();
	public boolean setRole(LemmingRole role, Position pos) throws OffBoardException, RoleParseException;
	public void load(String fileName) throws GameLoadException;
}
