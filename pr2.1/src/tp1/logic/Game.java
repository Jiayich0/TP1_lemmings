package tp1.logic;

import tp1.view.Messages;
import java.util.ArrayList;
import java.util.List;
import tp1.logic.gameobjects.*;

public class Game implements GameModel, GameStatus, GameWorld {
	
	private GameObjectContainer container;
	private int nLevel;
	
	private int initialLevel;
	private int numLemsGanar, numLemsEnTablero, numLemsMuertos, numLemsSalidos ;
	boolean exit = false;
	private int cycle;
	
	public static final int DIM_X = 10;		//no borrar, lo usa ConsoleView
	public static final int DIM_Y = 10;
	
	
	//Constructor
	public Game(int nLevel) {
		this.initialLevel = nLevel;
		initGameChooser();
	}
		
	//GameModel
	public boolean isFinished() {
		return exit || numLemsEnTablero == 0;
	}
	
	public void update() {
	    cycle++;  
	    container.update();
	}
	
	public void reset() {
		initGameChooser();
	}
	
	public void exit() {
		this.exit = true;
	}
	
	//GameStatus
	public int getCycle() {
		return cycle;
	}
	
	public int numLemmingsInBoard() {
		return numLemsEnTablero;
	}

	public int numLemmingsDead() {
		return numLemsMuertos;
	}

	public int numLemmingsExit() {
		return numLemsSalidos;
	}

	public int numLemmingsToWin() {
		return numLemsGanar;
	}

	public String positionToString(int col, int row) {
		Position pos = new Position(col, row);
		return container.positionToString(pos);
	}

	public boolean playerWins() {
		return (numLemsEnTablero == 0 && numLemsSalidos >= numLemsGanar);
	}

	public boolean playerLooses() {
		return numLemsMuertos > 0;
	}
	
	//GameWorld (callbacks)
	public boolean isInAir(Position pos) {
        Position posAbajo = pos.move(Direction.DOWN);
        return !isWall(posAbajo);
    }

	public boolean lemmingArrived(Position pos) {
		return isExitDoor(pos);
	}
	
	
	public boolean isInBoard(Position pos) {
		int col = pos.getCol();
		int row = pos.getRow();
		return col >= 0 && col < DIM_X && row >= 0 && row < DIM_Y;
	}
	
	public boolean isWall(Position pos) {		
		for (GameObject obj: container.getObjects()){
			if(obj.isInPosition(pos) && obj.isSolid()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isExitDoor(Position pos) {
		for (GameObject obj: container.getObjects()){
			if(obj.isInPosition(pos) && obj.isExit()) {
				return true;
			}
		}
		return false;
	}

	public void exitLem() {
		numLemsEnTablero--;
		numLemsSalidos++;
	}
	
	public void muereLem() {
		numLemsEnTablero--;
		numLemsMuertos++;
	}
	
	
	
	//Otros métodos
	private void initGameChooser() {
		switch(initialLevel) {
			case 0: 
				initGame0(); 
				break;
			default: 
				initGame1(); 
		}
	}
	
	//initGames
	private void initGame0(){
		cycle = 0;
		container = new GameObjectContainer();
		numLemsGanar = 2;
		numLemsEnTablero = 0;
		numLemsMuertos = 0;
		numLemsSalidos = 0;
		
		Lemming lem1 = new Lemming(this, new Position(2, 3));
		container.add(lem1);
		numLemsEnTablero++;
	    Lemming lem2 = new Lemming(this, new Position(9, 0));
	    container.add(lem2);
	    numLemsEnTablero++;
	    Lemming lem3 = new Lemming(this, new Position(0, 8));
	    numLemsEnTablero++;
	    container.add(lem3);
	    

	    // Crear algunas Walls de prueba
	    container.add(new Wall(this, new Position(8, 1)));
	    container.add(new Wall(this, new Position(9, 1)));
	    container.add(new Wall(this, new Position(2, 4)));
	    container.add(new Wall(this, new Position(3, 4)));
	    container.add(new Wall(this, new Position(4, 4)));
	    container.add(new Wall(this, new Position(4, 6)));
	    container.add(new Wall(this, new Position(5, 6)));
	    container.add(new Wall(this, new Position(6, 6)));
	    container.add(new Wall(this, new Position(7, 6)));
	    container.add(new Wall(this, new Position(7, 5)));
	    container.add(new Wall(this, new Position(0, 9)));
	    container.add(new Wall(this, new Position(1, 9)));
	    container.add(new Wall(this, new Position(8, 8)));
	    container.add(new Wall(this, new Position(8, 9)));
	    container.add(new Wall(this, new Position(9, 9)));
	    
	    // Crear una puerta de salida
	    ExitDoor exit = new ExitDoor(this, new Position(4, 5));
	    container.add(exit);
	}
	
	private void initGame1() {
		initGame0();
		container.add(new Lemming(this, new Position(3, 3)));
		numLemsEnTablero++;
	}




	
	// TODO you should write a toString method to return the string that represents the object status
	// public String toString()
	// en GameStatus
}
	

