package tp1.logic;

import tp1.exceptions.*;
import tp1.logic.gameobjects.*;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.lemmingRoles.ParachuterRole;
import tp1.view.Messages;


public class Game implements GameModel, GameStatus, GameWorld {
	
	private GameObjectContainer container;
	
	private int nLevel;
	private int numLemsGanar, numLemsEnTablero, numLemsMuertos, numLemsSalidos ;
	boolean exit = false;
	private int cycle;
	private GameConfiguration conf = FileGameConfiguration.NONE;
	
	public static final int DIM_X = 10;
	public static final int DIM_Y = 10;
	
	
	
	public Game(int nLevel) {
		this.nLevel = nLevel;
		initGames();
	}
		
	// GameModel
	public boolean isFinished() {												// Finish - o Sale o No quedan Lemmings
		return exit || numLemsEnTablero == 0;
	}
	
	public void update() {														// LLamado por execute() de NoParamsC y SetRoleC
	    cycle++;  
	    container.update();
	}
	
	public void reset() {
		if (conf == FileGameConfiguration.NONE) {
            initGames();														// Inicializar Default
        } else {
            initFromConfiguration(conf);									// Inicializar con conf
        }
	}
	
	private void initFromConfiguration(GameConfiguration conf) {
        this.cycle = conf.getCycle();
        this.numLemsEnTablero = conf.numLemmingsInBoard();
        this.numLemsMuertos = conf.numLemmingsDead();
	    this.numLemsSalidos = conf.numLemmingsExit();
	    this.numLemsGanar = conf.numLemmingsToWin();
	    
	    this.container = conf.getGameObjects();
    }
	
	public void exit() {														// Actualiza 'exit' 
		this.exit = true;
	}
	
	public boolean setRole(LemmingRole role, Position pos) throws OffBoardException, RoleParseException {	// Llamado por SetRoleCommand
		if(!isInBoard(pos)) {
			int row = pos.getRow();
			int col = pos.getCol();
			throw new OffBoardException(Messages.ROLE_OFF_BOARD_ERROR.formatted(
					Messages.POSITION.formatted(row, col)));
		}
		return container.setRole(role, pos);
	}
	
	
	// GameStatus
	public int getCycle() {														// Getter 'ciclo'
		return cycle;
	}
	
	public int numLemmingsInBoard() {											// Getters 'num...Board', ''num...Dead', etc
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

	public String positionToString(int row, int col) {							// Llamado por ConSoleView
		Position pos = new Position(row, col);
		return container.positionToString(pos);
	}

	public boolean playerWins() {												// Condición de Ganar
		return (numLemsEnTablero == 0 && numLemsSalidos >= numLemsGanar);
	}

	public boolean playerLooses() {
		return (numLemsEnTablero ==  0 && numLemsSalidos < numLemsGanar);		// Condición de Perder
	}
	
	// GameWorld (callbacks)
	public boolean isInAir(Position pos) {										// Para caminaOCae()
        Position posAbajo = pos.move(Direction.DOWN);
        return !isWall(posAbajo);
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
	
	public boolean lemmingArrived(Position pos) {								// Para caminaOCae() - pero es omitible
		return isExitDoor(pos);													// igual que IsExitDoor
	}
	
	public void exitLem() {	
		numLemsEnTablero--;
		numLemsSalidos++;
	}
	
	public void muereLem() {
		numLemsEnTablero--;
		numLemsMuertos++;
	}
		
	// Interaction 
	public boolean receiveInteractionsFrom(GameItem object) {					// Llamado por Lemming
		return container.checksInteractionsFrom(object);
	}
	
	// Ficheros
	public void load(String fileName) throws GameLoadException {
		this.conf = new FileGameConfiguration(fileName, this);
		initFromConfiguration(this.conf);
	}

		
	
	
	// Los initGames	
	private void initGames() {
		switch (nLevel) {
		case 0:
			initGame0();
			break;
		case 1:
			initGame1();
			break;
		default:
			initGame2();
			break;
	}
	}
	private void initGame0(){
		cycle = 0;
		container = new GameObjectContainer();
		numLemsGanar = 2;
		numLemsEnTablero = 0;
		numLemsMuertos = 0;
		numLemsSalidos = 0;
		
		
		// Lemmings - 3
		container.add(new Lemming(this, new Position(3, 2)));
		numLemsEnTablero++;
		container.add(new Lemming(this, new Position(0, 9)));
		numLemsEnTablero++;
		container.add(new Lemming(this, new Position(8, 0)));
		numLemsEnTablero++;
		
		// Walls Iniciales
		container.add(new Wall(this, new Position(1, 8)));
		container.add(new Wall(this, new Position(1, 9)));
		container.add(new Wall(this, new Position(4, 2)));
		container.add(new Wall(this, new Position(4, 3)));
		container.add(new Wall(this, new Position(4, 4)));
		container.add(new Wall(this, new Position(5, 7)));
		container.add(new Wall(this, new Position(6, 4)));
		container.add(new Wall(this, new Position(6, 5)));
		container.add(new Wall(this, new Position(6, 6)));
		container.add(new Wall(this, new Position(6, 7)));
		container.add(new Wall(this, new Position(8, 8)));
		container.add(new Wall(this, new Position(9, 0)));
		container.add(new Wall(this, new Position(9, 1)));
		container.add(new Wall(this, new Position(9, 8)));
		container.add(new Wall(this, new Position(9, 9)));
		
		 // ExitDoor
		container.add(new ExitDoor(this, new Position(5, 4)));
	}
	
	private void initGame1() {
		initGame0();
		
		// Nuevo Lemming - 4		
		container.add(new Lemming(this, new Position(3, 3)));
		numLemsEnTablero++;
	}
	
	private void initGame2() {	
		initGame1();
		
		// Nuevo Lemming - 5
		container.add(new Lemming(this, new Position(0, 6)));
		numLemsEnTablero++;
		
		// Nuevo Parachuter Lemming - 6
		container.add(new Lemming(this, new Position(0, 6), Direction.RIGHT, 0, new ParachuterRole()));
		numLemsEnTablero++;
		
		// Nuevo Wall
	    container.add(new Wall(this, new Position(5, 3)));
	    
	    // Nuevo MetalWall
		container.add(new MetalWall(this, new Position(6, 3)));
		
	}
}
	

