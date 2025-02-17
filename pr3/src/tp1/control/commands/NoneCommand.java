package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class NoneCommand extends NoParamsCommand{

	private static final String NAME = Messages.COMMAND_NONE_NAME;
	private static final String SHORTCUT = Messages.COMMAND_NONE_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_NONE_DETAILS;
	private static final String HELP = Messages.COMMAND_NONE_HELP;

	public NoneCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP); 
	}

	public void execute(GameModel game, GameView view) {								// Execute de NoneC/UpdateC
		game.update();
		view.showGame();
	}
	
	public boolean matchCommandName(String name) {								// 'name' / 'shortcut' / vacío == 'name'
		return getShortcut().equalsIgnoreCase(name) || getName().equalsIgnoreCase(name)
				||  isBlank(name);
	}
	
	private static boolean isBlank(String s) {									// Vacío o Espacios blancos
	    return (s == null) || (s.trim().length() == 0);
	}
}
