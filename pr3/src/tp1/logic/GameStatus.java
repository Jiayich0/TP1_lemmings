package tp1.logic;

public interface GameStatus {														// Para View

	public int getCycle();
	public int numLemmingsInBoard();
	public int numLemmingsDead();
	public int numLemmingsExit();
	public int numLemmingsToWin();

	public String positionToString(int row, int col);

	public boolean playerWins();
	public boolean playerLooses();
}
