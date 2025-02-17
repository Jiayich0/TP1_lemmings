package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.*;

public abstract class AbstractRole implements LemmingRole{
	
	private String name;
	private String help;
	private String shortcut;
	
	public AbstractRole(String name, String help, String shortcut) {
		this.name = name;
		this.help = help;
		this.shortcut = shortcut;
	}
	
	public abstract void play(Lemming lemming);										// Play de cada Role
	
	public abstract String getIcon(Lemming lemming);								// Getter 'icon' de cada Role
	
	public String getName() {														// Getter 'name'
		return name;
	}
	
	public String getHelp() {														// Getter 'help'
		return help;
	}
	
	public String getShortcut() {													// Getter 'shortcut'
		return shortcut;
	}
	
	public boolean matchRoleName(String name) {										
		return getShortcut().equalsIgnoreCase(name) || getName().equalsIgnoreCase(name);
	}
	
	public LemmingRole parse(String roleWords) {									// Factory llama a este parse()
		if(matchRoleName(roleWords)) {
			return createInstance();
		}
		return null;
	}
	
	protected abstract LemmingRole createInstance();								// Para evitar GetClass()
	
	//interact
	public boolean receiveInteraction(GameItem other, Lemming lemming) { return false; }	// Default Todo en False
	
	public boolean interactWith(Lemming receiver, Lemming lemming) { return false; }
	public boolean interactWith(Wall wall, Lemming lemming) { return false; }
	public boolean interactWith(ExitDoor door, Lemming lemming) { return false; }
	public boolean interactWith(MetalWall metalWall, Lemming lemming) { return false; }
}
