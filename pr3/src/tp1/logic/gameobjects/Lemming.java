package tp1.logic.gameobjects;


import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.*;
import tp1.logic.lemmingRoles.*;
import tp1.view.Messages;


public class Lemming extends GameObject {
	
	private static final String NAME = Messages.LEMMING_NAME;
	private static final String SHORTCUT = Messages.LEMMING_SHORTCUT;
	private LemmingRole role;
	private Direction dir;

	private int fuerzaCaida;
	
	
	
	public Lemming(GameWorld game, Position pos) {
		super(game, pos);
		this.role = new WalkerRole();
		this.dir = Direction.RIGHT;
		fuerzaCaida = 0;
	}
	
	public Lemming(GameWorld game, Position pos, Direction dir, int fuerzaCaida, LemmingRole role) {
		super(game, pos);
		this.dir = dir;
		this.fuerzaCaida = fuerzaCaida;
		this.role = role;
	}
	
	public Lemming() {																// Constructor para GameObjectFactory
		super(null, null);
	}
	
	public String getName() {														// Getter 'NAME'
		return NAME;
	}
	
	public String getShortcut() {													// Getter 'SHORTCUT'
		return SHORTCUT;
	}
	
	public GameObject createNew(GameWorld game, Position pos) {						// No se llega a usar
		return new Lemming(game, pos);
	}
	
	public GameObject copy() {
		return new Lemming(this.game, this.pos, this.dir, this.fuerzaCaida, this.role);
	}
	
	// Not mandatory but recommended
	public void caminaOCae() {														// Play de WalkerRole
		if(lemmingArrived()) {
			game.exitLem();
			isAlive = false;
		}
		else {
			if(isInAir())
				fall();
			else {
				if(enCaida())
					land();
				if(isAlive)
					camina();
			}
		}		
	}
	
	//public void start() {
	//	role.start(this);
	//}
	
	public void update() {															// Actualiza todas las Interacciones antes de play
		checkInteractions();
		if(isAlive)
			role.play(this);
	}
	
	public String getIcon() {														// Getter 'roleIcon'
		return role.getIcon(this);
	}
	
	// Los callbacks
	public boolean isInAir() {
        return game.isInAir(pos);
    }

	public boolean lemmingArrived() {
		return game.lemmingArrived(pos);
	}

	public Direction getDirection() {												// Getter Direction
		return dir;
	}
	
	public boolean isInPosition(Position p) {										// pos = p
		return pos.equals(p);
	}
	
	// Métodos para caminaOCae
	public void land() {
		if(fuerzaCaida > 2) {
			isAlive = false;
			game.muereLem();
		}
		else
			isAlive = true;
		fuerzaCaida = 0;
	}
	
	public boolean enCaida() {
		return fuerzaCaida > 0;
	}
	
	public void fall() {
		pos = pos.move(Direction.DOWN);
		fuerzaCaida++;
		isAlive = game.isInBoard(pos);
		if (isAlive == false)
			game.muereLem();
	}
	
	public void camina() {
		fuerzaCaida = 0;
		
		Position tentativa = pos.move(dir);
		
		if(!game.isWall(tentativa) && game.isInBoard(tentativa)){
			pos = tentativa;
		}
		else {
			dir = dir.opuesta();
		}
	}
	
	public void setFallForce(int force) {											// Para ParachuterRole
		this.fuerzaCaida = force;
	}
	
	public boolean setRole(LemmingRole role) {	// Container llama a este setRole
		if(!this.role.getName().equals(role.getName())){
			this.role = role;
			//role.start(this);
	        return true;
		}
		return false;
	}

	public void disableRole() {														// Default Role - WalkerRole
		this.role = new WalkerRole();
	}
	
	public boolean isOver(GameObject obj) {											// Check si debajo es Wall
		return obj.isInPosition(pos.move(Direction.DOWN));
	}
	
	// Ficheros
	public GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException { // Sobreescribe Parse de GameObject
		String[] words = line.trim().split("\\s+");
		
		try {
			if(!getName().equalsIgnoreCase(words[1]) && !getShortcut().equalsIgnoreCase(words[1])) {
				return null;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ObjectParseException(Messages.INVALID_GAME_OBJECT.formatted(line));
		}
		
		Position pos = getPositionFrom(line, game);								// ObjectParseException - OffBoardException
		Direction dir = getLemmingDirectionFrom(line);							// ObjectParseException
    	int height = getLemmingHeigthFrom(line);								// ObjectParseException
    	LemmingRole role = getLemmingRoleFrom(line);							// ObjectParseException
    	
    	return new Lemming(game, pos, dir, height, role);
	}
	
	// Interactions
	public boolean checkInteractions() {											// CheckInteractions antes de Play
		return game.receiveInteractionsFrom(this);
	}
	
	public boolean receiveInteraction(GameItem other){								// Interacciones 
		 return this.role.receiveInteraction(other, this);
	}
	
	public boolean interactWith(Lemming lemming) { 
		return this.role.interactWith(lemming, this);
	}
	
	public boolean interactWith(Wall wall) {
		return this.role.interactWith(wall, this);
	}
	public boolean interactWith(ExitDoor exitDoor) {
		return this.role.interactWith(exitDoor, this);
	}
	public boolean interactWith(MetalWall metalWall) {
		return this.role.interactWith(metalWall, this);
	}
}


