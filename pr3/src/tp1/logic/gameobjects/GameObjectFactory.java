package tp1.logic.gameobjects;

import java.util.Arrays;
import java.util.List;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.GameWorld;
import tp1.view.Messages;

public class GameObjectFactory {
	
	private static final List<GameObject> availableObjects 							// Lista de Objectos Disponibles
		= Arrays.asList(
			new Wall(),
	        new MetalWall(),
	        new ExitDoor(),
	        new Lemming()
	);
	
	public static GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException { // Parse(): Recorre la Lista de Objects
		for (GameObject obj: availableObjects) {									// Si coincide nombre: devuelve Clase
			GameObject parsedObject = obj.parse(line, game);
			if (parsedObject != null) {
				return parsedObject;
	        }
		}
		throw new ObjectParseException(Messages.UNKNOWN_GAME_OBJECT.formatted(line));
	}
}