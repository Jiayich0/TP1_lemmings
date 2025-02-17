package tp1.logic;

public interface GameModel {	//para Controller
	
	public boolean isFinished();
	public void update();
	public void reset();
	public void exit();
}
