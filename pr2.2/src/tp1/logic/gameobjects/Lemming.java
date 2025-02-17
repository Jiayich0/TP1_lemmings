package tp1.logic.gameobjects;

import tp1.logic.*;
import tp1.logic.lemmingRoles.*;

public class Lemming extends GameObject {
	
	private LemmingRole role;
	private Direction dir;

	private int fuerzaCaida;

	
	public Lemming(GameWorld game, Position pos) {
		super(game, pos);
		this.role = new WalkerRole();
		this.dir = Direction.RIGHT;
		fuerzaCaida = 0;
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
	
	public String getIcon() {														// Getter Icon
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
	
	public boolean setRole(LemmingRole role) {										// Container llama a este setRole
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


