package tp1.logic;				//mirar esto

import java.util.*;
import tp1.logic.gameobjects.*;
import tp1.view.Messages;

public class GameObjectContainer {
	//TODO fill your code
	private List<Lemming> lemmingList;
	private List<Wall> wallList;
	private ExitDoor exitDoor;
	
	
	
	public GameObjectContainer() {
		lemmingList = new ArrayList<>();
		wallList = new ArrayList<>();
		exitDoor = null;
	}
	
	
	
	public List<Lemming> getLemmingList() {					//getter lista de lemmings
		return lemmingList;
	}
	
	public List<Wall> getWallList() {						//getter lista de walls
		return wallList;
	}
	
	public ExitDoor getExitDoor() {							//getter de exitDoor
		return exitDoor;
	}
	
	public void add(Lemming lemming) {						//añadir y quitar lemming, wall y exitDoor
		lemmingList.add(lemming);
	}
	
	public void remove(Lemming lemming){
		lemmingList.remove(lemming);
	}
	
	public void add(Wall wall) {
		wallList.add(wall);		
	}
	
	/*public void remove(Wall wall) {
		wallList.remove(wall);
	}*/
	
	public void add(ExitDoor exitDoor) {
		this.exitDoor = exitDoor;
	}
	
	/*public int contLemsVivos() {									//contadores
		int cont = 0; 
		for (Lemming lemming : lemmingList) {
			if (lemming.getEstaVivo() && !lemming.getPosition().equals(exitDoor.getPosition())) {
				cont++;
			}
		}
		return cont;
	}
	
	public int contLemsMuertos() {
		int cont = 0;
		for (Lemming lemming: lemmingList) {
			if (!lemming.getEstaVivo()) {
				cont++;
			}
		}
		return cont;
	}*/
	
	//SALIRSE--------------------------------------------------------
	//SI NO SE ELIMINA DE LA LISTA - SALIRSE
	/*public int contLemsSalidos() {
		int cont = 0;
		if (exitDoor != null) {										//en caso de que exitDoor sea null, para hacer pruebas
			Position posSalida = exitDoor.getPosition();
			for (Lemming lemming: lemmingList) {
				if(lemming.getPosition().equals(posSalida)){
					cont++;
				}
			}
		}
		return cont;
	}*/
	
	 /*public int getContVivos() {									//intento de simplificar los tres buscadores
		 return contVivos;
	 }
	 public int getContMuertos() {
		 return contMuertos;
	 }
	 public int getContExits() {
		 return contExits;
	 }*/
	 
	public int contadorLems() {								//contador de lemmings total en partida no se si se usa ya que nodiferencia vivos, muertos, salida
	    return lemmingList.size();
	}
	
	public void update() {									//update de todo el tablero
		for(Lemming lemming: lemmingList) {
			lemming.update();
		}
		removeMuertos();
		
		/*for(Wall wall: wallList) {
			wall.update();
		}
		if(exitDoor != null) {
			exitDoor.update();
		}*/
	}
	
	public void removeMuertos () {
		int i = lemmingList.size()-1;
		while (i >= 0){
            Lemming currentObject = lemmingList.get(i);

            if (!currentObject.getEstaVivo()) { 
                lemmingList.remove(i);
            }
            i--;
        }
    }
	
	
	
	
	public String positionToString(Position p) {			//muestra a todos los lemmings en una position
		StringBuilder sb = new StringBuilder();
		
		for(Lemming lem:lemmingList) {
			if(lem.isInPosition(p)) {
				sb.append(lem.getIcon());
			}
		}
		
		for(Wall wall : wallList) {
	        if(wall.getPosition().equals(p)) {
	            sb.append(wall.getIcon());
	        }
	    }
		
		if(exitDoor != null && exitDoor.getPosition().equals(p)) {
			sb.append(exitDoor.getIcon());
		}
		
		if (sb.length() == 0) {
	        sb.append(Messages.EMPTY);
	    }
		
		return sb.toString();
	}
	
}
