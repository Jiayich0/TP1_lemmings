package tp1.logic;

public interface GameConfiguration {
	   // game status
	   public int getCycle();
	   public int numLemmingsInBoard();
	   public int numLemmingsDead();
	   public int numLemmingsExit();
	   public int numLemmingsToWin();
	   // game objects: NO es una rotura de encapsulación
	   public GameObjectContainer getGameObjects();
}
