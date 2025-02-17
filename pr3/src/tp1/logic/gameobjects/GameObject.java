package tp1.logic.gameobjects;

import tp1.exceptions.*;
import tp1.logic.Direction;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.lemmingRoles.LemmingRoleFactory;
import tp1.view.Messages;

public abstract class GameObject implements GameItem{

	protected Position pos;
	protected boolean isAlive;
	protected GameWorld game;
	
	public GameObject(GameWorld game, Position pos) {
		this.isAlive = true;
		this.pos = pos;
		this.game = game;
	}
	
	// interact
	public boolean receiveInteraction(GameItem other) { return false; }				// Default Todo en False
	
	public boolean interactWith(Lemming lemming) { return false; }
	public boolean interactWith(Wall wall) { return false; }
	public boolean interactWith(ExitDoor door) { return false; }
	public boolean interactWith(MetalWall metalWall) { return false; }
	
	// pr2.1
	public boolean isSolid() {														// Default False - Wall & MetalWall True
		return false;
	}
	
	public boolean isAlive() {														// Estado de 'isAlive'
		return isAlive;
	}
	
	public boolean isExit() {														// Default False - ExitDoor True
		return false;
	}
	
	public boolean isInPosition(Position p) {										// 'pos' == 'p'
		return pos.equals(p);
	}
	
	public abstract void update();													// Solo Lemming tiene update() (Actualmente)
	
	public abstract String getIcon();												// Getter 'icon' de cada Object
	
	public boolean setRole(LemmingRole role) throws RoleParseException {										// Default False - Lemming lo implementa
		return false;
	}
	
	public void kill() {															// Estado de 'isAlive' a False
		isAlive = false;
	}
	
	public abstract String getName();												// Getter 'name'
	
	public abstract String getShortcut();											// Getter 'shortcut'
	
	public abstract GameObject createNew(GameWorld game, Position pos);				// new Wall, MetalWall, ExitDoor
	
	public abstract GameObject copy();												// Copia de objetos
	
	// Ficheros
	public GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException {
		String[] words = line.trim().split("\\s+");
		
		try {
			if(!getName().equalsIgnoreCase(words[1]) && !getShortcut().equalsIgnoreCase(words[1])) {
				return null;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ObjectParseException(Messages.INVALID_GAME_OBJECT.formatted(line));
		}
		
		
		Position pos = getPositionFrom(line, game);								// ObjectParseException - OffBoardException
    	
    	return createNew(game, pos);
	}
	
	protected static Position getPositionFrom(String line, GameWorld game) throws ObjectParseException, OffBoardException {
		Position pos;
		
		String[] word = line.trim().split("\\s+");
		
		String[] words = word[0].replace("(", " ").replace(",", " ").replace(")", " ").strip().split("( )+");
		try {
			int row = Integer.parseInt(words[0]);			// Char from 'line' -> to String -> to Int
			int col = Integer.parseInt(words[1]);
			pos = new Position(row, col);
			
			if(!game.isInBoard(pos)) {
				throw new OffBoardException(Messages.OBJECT_OFF_WORLD_POSITION.formatted(line));
			}
		} catch (NumberFormatException e) {
			throw new ObjectParseException(Messages.INVALID_POSITION.formatted(line));
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ObjectParseException(Messages.INVALID_GAME_OBJECT.formatted(line));
		}

		return pos;	
	}
	
	protected static Direction getLemmingDirectionFrom(String line) throws ObjectParseException {
		String[] word = line.trim().split("\\s+");
		
		Direction dir = Direction.stringToDir(word[2]);
		if(dir == null) {
			throw new ObjectParseException(Messages.UNKNOWN_DIRECTION.formatted(line));
		}
		
		if(dir != Direction.LEFT && dir != Direction.RIGHT) {
			throw new ObjectParseException(Messages.INVALID_LEM_DIR.formatted(line));
		}
		
		return dir;
	}
	
	protected static int getLemmingHeigthFrom(String line) throws ObjectParseException {
		String[] word = line.trim().split("\\s+");
		
		try {
			return Integer.parseInt(word[3]);
		} catch (NumberFormatException e) {
			throw new ObjectParseException(Messages.INVALID_HEIGHT.formatted(line));
		}
	}
	
	protected static LemmingRole getLemmingRoleFrom(String line) throws ObjectParseException {
		String[] word = line.trim().split("\\s+");
		
		try {
			return LemmingRoleFactory.parse(word[4]);
		} catch (RoleParseException e) {
			throw new ObjectParseException(Messages.INVALID_LEM_ROLE.formatted(line));
		}
	}

}
