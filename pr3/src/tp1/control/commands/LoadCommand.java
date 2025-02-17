package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.GameLoadException;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class LoadCommand extends Command {
	
    private static final String NAME = Messages.COMMAND_LOAD_NAME;
    private static final String SHORTCUT = Messages.COMMAND_LOAD_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_LOAD_DETAILS;
    private static final String HELP = Messages.COMMAND_LOAD_HELP;
    
    private String fileName;
    
    public LoadCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
    
    public LoadCommand(String fileName) {												// Constructor con Parámetros
    	super(NAME, SHORTCUT, DETAILS, HELP);
    	this.fileName = fileName;
    }
    
    public Command parse(String[] commandWords) throws CommandParseException {			// Parse Llamado por Generator
    	
    	if (commandWords.length < 1 || !matchCommandName(commandWords[0])) {			// Si comando no existe devuelve null para CommandGenerator
			return null;
		}
    	
    	if(commandWords.length != 2) {													// Si nº params incorrecto lanza ParseException
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
    	this.fileName = commandWords[1];
    	return new LoadCommand(commandWords[1]);
    }
    
    public void execute(GameModel game, GameView view) throws CommandExecuteException {	// Execute de LoadC
		try {
	        game.load(fileName);
	        view.showGame();
		 } catch (GameLoadException e) {
			 throw new CommandExecuteException(Messages.INVALID_FILE.formatted(fileName), e);
		 }
	}
    
}