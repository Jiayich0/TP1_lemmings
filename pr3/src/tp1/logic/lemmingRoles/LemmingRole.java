package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.*;

public interface LemmingRole {
	
	public default void start( Lemming lemming ) {}		// En caso de implementar, quitar default y {} e implementar en cada rol
	public void play( Lemming lemming );
	public String getIcon( Lemming lemming );
	
	public String getName();
	public String getHelp();
	public String getShortcut();
	public boolean matchRoleName(String name);
	public LemmingRole parse(String roleWords);
	
	public boolean receiveInteraction(GameItem other, Lemming lemming);
	
	public boolean interactWith(Lemming receiver, Lemming lemming);
	public boolean interactWith(Wall wall, Lemming lemming);
	public boolean interactWith(ExitDoor door, Lemming lemming);
	public boolean interactWith(MetalWall metalWall, Lemming lemming);
}
