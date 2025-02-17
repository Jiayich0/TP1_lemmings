package tp1.logic;

public interface GameWorld {	//para GameObject
	
	public boolean isInAir(Position pos);
	public boolean lemmingArrived(Position pos);
	
	public boolean isInBoard(Position pos);
	public boolean isExitDoor(Position pos);
	public boolean isWall(Position pos);
	
	public void exitLem();
	public void muereLem();
}
