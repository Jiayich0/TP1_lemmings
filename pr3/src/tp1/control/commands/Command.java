package tp1.control.commands;


import tp1.exceptions.*;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public abstract class Command {

	// Forman parte de atributos de estado
	private final String name;
	private final String shorcut;
	private final String details;
	private final String help;
	
	public Command(String name, String shorcut, String details, String help) {
		this.name = name;
		this.shorcut = shorcut;
		this.details = details;
		this.help = help;
	}

	protected String getName() { return name; }										// Getters 
	protected String getShortcut() { return shorcut; }
	protected String getDetails() { return details; }
	protected String getHelp() { return help; }
	
	public abstract void execute(GameModel game, GameView view) throws CommandExecuteException;
	public abstract Command parse (String[] commandWords) throws CommandParseException;
	
	protected boolean matchCommandName(String name) {								// 'name' == 'name' (distinto solo en NoneCommand)
		return getShortcut().equalsIgnoreCase(name) || getName().equalsIgnoreCase(name);
	}

	public String helpText(){														// Mismo helpText() para NoParamsC
		return Messages.LINE_TAB.formatted(Messages.COMMAND_HELP_TEXT.formatted(getDetails(), getHelp()));
	}
}
