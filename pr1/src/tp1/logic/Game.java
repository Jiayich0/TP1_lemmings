package tp1.logic;

import tp1.view.Messages;
import java.util.ArrayList;
import java.util.List;
import tp1.logic.gameobjects.*;

public class Game {
	
	public static final int DIM_X = 10;
	public static final int DIM_Y = 10;
	private int numLemsGanar, numLemsEnTablero, numLemsMuertos, numLemsSalidos ;
	boolean exit = false;
	private int initialLevel;
	
	
	private int ciclo;
	private GameObjectContainer container;
	//SALIRSE-------------------------------------------------------

	
	
	public Game(int nLevel) {
		this.initialLevel = nLevel;
		initGameChooser();
	}
	
	public void reset() {
		initGameChooser();
	}
	
	private void initGameChooser() {
		switch(initialLevel) {
			case 0: 
				initGame0(); 
				break;
			default: 
				initGame1(); 
		}
		/*if(initialLevel == 0) {
			initGame0();
		}
		else 
			initGame1();*/
	}
	
	private void initGame0(){
		// TODO Auto-generated constructor stub
				ciclo = 0;
				container = new GameObjectContainer();
				numLemsGanar = 2;
				numLemsEnTablero = 0;
				numLemsMuertos = 0;
				numLemsSalidos = 0;
				
				Lemming lem1 = new Lemming(new Position(2, 3), this);
				container.add(lem1);
				numLemsEnTablero++;
			    Lemming lem2 = new Lemming(new Position(9, 0), this);
			    container.add(lem2);
			    numLemsEnTablero++;
			    Lemming lem3 = new Lemming(new Position(0, 8), this);
			    numLemsEnTablero++;
			    container.add(lem3);
			    

			    // Crear algunas Walls de prueba
			    container.add(new Wall(new Position(8, 1)));
			    container.add(new Wall(new Position(9, 1)));
			    container.add(new Wall(new Position(2, 4)));
			    container.add(new Wall(new Position(3, 4)));
			    container.add(new Wall(new Position(4, 4)));
			    container.add(new Wall(new Position(4, 6)));
			    container.add(new Wall(new Position(5, 6)));
			    container.add(new Wall(new Position(6, 6)));
			    container.add(new Wall(new Position(7, 6)));
			    container.add(new Wall(new Position(7, 5)));
			    container.add(new Wall(new Position(0, 9)));
			    container.add(new Wall(new Position(1, 9)));
			    container.add(new Wall(new Position(8, 8)));
			    container.add(new Wall(new Position(8, 9)));
			    container.add(new Wall(new Position(9, 9)));
			    
			    // Crear una puerta de salida
			    ExitDoor exit = new ExitDoor(new Position(4, 5));
			    container.add(exit);
	}
	
	private void initGame1() {
		initGame0();
		container.add(new Lemming(new Position(3, 3), this));
		numLemsEnTablero++;
	}
	
	public GameObjectContainer getContainer() {
		return container;
	}
	
	public int getCycle() {
		return ciclo;
	}

	public int numLemmingsInBoard() {
		return numLemsEnTablero;
	}

	public int numLemmingsDead() {
		return numLemsMuertos;
	}
	
	public void muereLem() {
		numLemsEnTablero--;
		numLemsMuertos++;
	}
	//SALIRSE--------------------------------------------------------
	public int numLemmingsExit() {
		return numLemsSalidos;
	}

	public int numLemmingsToWin() {
		// TODO Auto-generated method stub
		return numLemsGanar;
	}

	public String positionToString(int col, int row) {
		// TODO Auto-generated method stub
		Position pos = new Position(col, row);
		return container.positionToString(pos);
	}

	public boolean playerWins() {
		// TODO Auto-generated method stub
		return (numLemsEnTablero == 0 && numLemsSalidos >= numLemsGanar);
	}

	public boolean playerLooses() {
		// TODO Auto-generated method stub
		return numLemsMuertos > 0;
	}

	public String help() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean esWall(Position pos) {							//para saber si es pared
		for (Wall wall: container.getWallList()) {
			if(wall.getPosition().equals(pos)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isFinished() {
		return exit || numLemsEnTablero == 0;
	}
	
	
	public void exit() {
		this.exit = true;
	}
	
	public boolean esEnTablero(Position pos) {
		int col = pos.getCol();
		int row = pos.getRow();
		return col >= 0 && col < DIM_X && row >= 0 && row < DIM_Y;
	}
	
	public boolean esExitDoor(Position pos) {
		return container.getExitDoor().getPosition().equals(pos);
	}
	
	public void exitLem() {
		numLemsEnTablero--;
		numLemsSalidos++;
	}
	
	public void update() {
	    ciclo++;  
	    container.update();
	}

}
