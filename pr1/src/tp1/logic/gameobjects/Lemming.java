package tp1.logic.gameobjects;

import tp1.logic.*;
import tp1.logic.lemmingRoles.*;

public class Lemming {

	//TODO fill your code
	private Position pos;
	private boolean estaVivo;
	private Direction dir;
	private int fuerzaCaida;
	private WalkerRole rol;
	private Game game;
	
	
	
	public Lemming(Position pos, Game game) {
		this.pos = pos;
		estaVivo = true;
		dir = Direction.RIGHT;			//por defecto
		fuerzaCaida = 0;
		rol = new WalkerRole();
		this.game = game;
	}
	
	
	
	public Position getPosition() {		//position getter Lemmings
		return pos;
	}
	
	public boolean getEstaVivo() {		//estaVivo getter Lemmings
		return estaVivo;
	}
	
	public Direction getDirection() {	//direction getter Lemings
		return dir;
	}
	
	public int getFuerzaCaida() {		//fuerzaCaida getter lemmings
		return fuerzaCaida;
	}
	
	public WalkerRole getRol() {		//rol getter lemmings
		return rol;
	}
	
	public void camina() {
		fuerzaCaida = 0;						//preguntar si esto esta bien, técnicamnente si camina es que la fuerzaCaida es 0, pero no sabemos si el reset de fuerzaCaida se haria aqui o en el update (u otro lado)
		
		Position tentativa = pos.move(dir);
		
		if(!game.esWall(tentativa) && game.esEnTablero(tentativa)){
			pos = tentativa;
		}
		else {
			dir = dir.opuesta();
		}
	}
	
	public void cae() {
		pos = pos.move(Direction.DOWN);
		fuerzaCaida++;
		estaVivo = game.esEnTablero(pos);
		if (estaVivo == false)
			game.muereLem();
	}
	
	public void muere() {
		estaVivo = false;
		//game.getContainer().remove(this);
	}
	
	
	public boolean enAire() {
		Position posAbajo = pos.move(Direction.DOWN);
		return !game.esWall(posAbajo);
	}
	
	public boolean enExitDoor() {
		Position posActual = new Position(pos.getCol(), pos.getRow());
		return game.esExitDoor(posActual);
	}
	
	public boolean isInPosition(Position p) {
		return pos.equals(p);
		//return pos.getCol() == p.getCol() && pos.getRow() == p.getRow();
	}
	
	public boolean enCaida() {
		return fuerzaCaida > 0;
	}
	
	public void aterriza() {
		if(fuerzaCaida > 2) {
			estaVivo = false;
			game.muereLem();
		}
		else
			estaVivo = true;
		fuerzaCaida = 0;
	}

	public String getIcon() {
		return rol.getIcon(this);
	}
	/**
	 *  Implements the automatic update	
	 */
	public void caminaOCae() {
		//TODO fill your code
		if(game.esExitDoor(pos)) {
			game.exitLem();
			estaVivo = false;
		}
		else {
			if(enAire())
				cae();
			else {
				if(enCaida())
					aterriza();
				if(estaVivo)
					camina();
			}
		}
		//rol.play(this);					//comportamiento de un WalkerRole
		
	}
	
	public void update() {
		if(estaVivo)
			rol.play(this);
	}
}


