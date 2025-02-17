package tp1.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import tp1.exceptions.*;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.GameObjectFactory;
import tp1.view.Messages;


public class FileGameConfiguration implements GameConfiguration{
	
	private List<GameObject> gameObjects;
	
	private int cycle;
    private int numLemmingsInBoard;
    private int numLemmingsDead;
    private int numLemmingsExit;
    private int numLemmingsToWin;
    
    public static final GameConfiguration NONE = new FileGameConfiguration();
	
    public FileGameConfiguration() { }												// Constructor vacío
    
	public FileGameConfiguration(String fileName, GameWorld game) throws FileConfigException {
		
		try (BufferedReader entrada = new BufferedReader(new FileReader(fileName))) {
			this.gameObjects = new ArrayList<>();
			String line = entrada.readLine();
			parseConfig(line);
			
			GameObject obj;
			line = entrada.readLine();
			
			while (line != null && !line.isEmpty()) {
				obj = GameObjectFactory.parse(line, game);
				this.gameObjects.add(obj);
				line = entrada.readLine();
			}
		} catch (FileNotFoundException e) {
			throw new FileConfigException(Messages.FILE_NOT_FOUND.formatted(fileName));
		} catch (IOException e) {
			throw new FileConfigException(Messages.READ_ERROR.formatted(fileName));
		} catch (ObjectParseException | OffBoardException e) {
			throw new FileConfigException(e.getMessage());
		}
	}	
	
	public void parseConfig(String line) throws FileConfigException {
		try (Scanner scanner = new Scanner(line)) {
			cycle = scanner.nextInt();
	        numLemmingsInBoard = scanner.nextInt();
	        numLemmingsDead = scanner.nextInt();
	        numLemmingsExit = scanner.nextInt();
	        numLemmingsToWin = scanner.nextInt();

	        if (scanner.hasNext())
	            throw new FileConfigException(Messages.INVALID_GAME_STATUS.formatted(line));
	    } catch (NoSuchElementException e) {
	        throw new FileConfigException(Messages.INVALID_GAME_STATUS.formatted(line));
	    }
	}
	
	public int getCycle() {
		return cycle;
	}

	public int numLemmingsInBoard() {
		return numLemmingsInBoard;
	}

	public int numLemmingsDead() {
		return numLemmingsDead;
	}

	public int numLemmingsExit() {
		return numLemmingsExit;
	}

	public int numLemmingsToWin() {
		return numLemmingsToWin;
	}

	public GameObjectContainer getGameObjects() {
		GameObjectContainer cont = new GameObjectContainer();
		for(GameObject obj : gameObjects) {
			cont.add(obj.copy());
		}
		return cont;
	}
}
