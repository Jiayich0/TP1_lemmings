package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.lemmingRoles.LemmingRole;


public class GameObjectContainer {
	
	private List<GameObject> objects;

	
	
	public GameObjectContainer() {
		objects = new ArrayList<GameObject>();
	}
	
	public List<GameObject> getObjects(){											// Getter de la Lista de Objects
		return objects;
	}
	
	// Only one add method (polymorphism)
	public void add(GameObject object) {											// Adder
		objects.add(object);
	}

	public void remove(GameObject object) {											// Remover
		objects.remove(object);
	}
	
	// TODO you should write a toString method to return the string that represents the object status
	public String positionToString(Position p) {									// Muestra Todos los Lemmings en una 'pos'
		StringBuilder sb = new StringBuilder();										// Llamado por Game
		
		for(GameObject obj : objects) {
			if(obj.isInPosition(p)) {
				sb.append(obj.getIcon());
			}
		}
		return sb.toString();
	}
	
	/*public String toString() {													// No hace nada, lo hace positionToString()
		StringBuilder sb = new StringBuilder();
		for(GameObject obj : objects) {
			sb.append(obj.toString());
		}
		return sb.toString();
	}*/
	
	public void update() {															// Llamado por Game
		for(GameObject obj : objects) {
			if(obj.isAlive()) {
				obj.update();
			}
		}
		removeDeadObjects();
	}
	
	private void removeDeadObjects() { 												// Private - usado por update()
		int i = objects.size()-1;
		while (i >= 0){
            GameObject obj = objects.get(i);

            if (!obj.isAlive()) { 
                objects.remove(i);
            }
            i--;
        }
	}
	
	public boolean setRole(LemmingRole role, Position pos) {						// Llamado por Game
		for(GameObject obj : objects) {
			if(obj.isInPosition(pos) && obj.setRole(role)) {
				return true;
			}
		}
		return false;
	}
	
	// Interaction
	public boolean checksInteractionsFrom(GameItem other) {							// Llamado por Game
		boolean recibe = false;
		for (GameObject obj : objects) {
			if(obj.receiveInteraction(other)) {
				recibe = true;
			}
		}
		return recibe;
	}
}

