package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.GameObject;


public class GameObjectContainer {
	
	private List<GameObject> objects;

	
	
	public GameObjectContainer() {
		objects = new ArrayList<GameObject>();
	}
	
	public List<GameObject> getObjects(){		//getter
		return objects;
	}
	
	// Only one add method (polymorphism)
	public void add(GameObject object) {
		objects.add(object);
	}

	//TODO fill your code
	public void remove(GameObject object) {
		objects.remove(object);
	}
	
	// TODO you should write a toString method to return the string that represents the object status
	// no hace nada, lo hace positionToString
	/*public String toString() {
		StringBuilder sb = new StringBuilder();
		for(GameObject obj : objects) {
			sb.append(obj.toString());
		}
		return sb.toString();
	}*/
	
	
	public String positionToString(Position p) {			//muestra a todos los lemmings en una position
		StringBuilder sb = new StringBuilder();
		
		for(GameObject obj : objects) {
			if(obj.isInPosition(p)) {
				sb.append(obj.getIcon());
			}
		}
		return sb.toString();
	}
	
	public void update() {	
		for(GameObject obj : objects) {
			if(obj.isAlive()) {
				obj.update();
			}
		}
		removeDeadObjects();
	}
	
	private void removeDeadObjects() { 
		int i = objects.size()-1;
		while (i >= 0){
            GameObject obj = objects.get(i);

            if (!obj.isAlive()) { 
                objects.remove(i);
            }
            i--;
        }
	}
}

