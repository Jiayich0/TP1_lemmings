package tp1.logic;

import tp1.logic.gameobjects.GameItem;

public interface GameWorld {														// Para GameObject
	
	public boolean isInAir(Position pos);
	public boolean lemmingArrived(Position pos);
	
	public boolean isInBoard(Position pos);
	public boolean isExitDoor(Position pos);
	public boolean isWall(Position pos);
	
	public void exitLem();
	public void muereLem();
	
	public boolean receiveInteractionsFrom(GameItem object);
}
