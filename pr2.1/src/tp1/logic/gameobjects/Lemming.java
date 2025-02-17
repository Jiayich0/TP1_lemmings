package tp1.logic.gameobjects;

import tp1.logic.*;
import tp1.logic.lemmingRoles.*;

public class Lemming extends GameObject {
	
	private WalkerRole role;
	private Direction dir;

	private int fuerzaCaida;

	
	public Lemming(GameWorld game, Position pos) {
		super(game, pos);
		this.role = new WalkerRole();
		// TODO fill your code
		this.dir = Direction.RIGHT;			//por defecto
		this.pos = pos;
		isAlive = true;
		fuerzaCaida = 0;
	}
	
	// Not mandatory but recommended
	public void caminaOCae() {
		//TODO fill your code
		if(game.lemmingArrived(pos)) {
			game.exitLem();
			isAlive = false;
		}
		else {
			if(game.isInAir(pos)) //game.isInAir
				cae();
			else {
				if(enCaida())
					aterriza();
				if(isAlive)
					camina();
			}
		}		
	}
	
	/**
	 *  Implements the automatic update	
	 */
	public void update() {
		if(isAlive)
			role.play(this);
	}
	
	public String getIcon() {
		return role.getIcon(this);
	}

	
	// TODO you should write a toString method to return the string that represents the object status
	// public String toString()
		
	//Codigo de PR1
	
	public Direction getDirection() {	//direction getter
		return dir;
	}
	
	public boolean isInPosition(Position p) {
		return pos.equals(p);
	}
	
	//para caminaOCae
	public void aterriza() {
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
	
	public void cae() {
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
}


