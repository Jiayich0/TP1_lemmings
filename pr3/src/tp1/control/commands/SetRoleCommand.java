package tp1.control.commands;

import tp1.exceptions.*;
import tp1.logic.GameModel;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.lemmingRoles.LemmingRoleFactory;
import tp1.view.GameView;
import tp1.view.Messages;

public class SetRoleCommand extends Command {

	private static final String NAME = Messages.COMMAND_SETROLE_NAME;
	private static final String SHORTCUT = Messages.COMMAND_SETROLE_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_SETROLE_DETAILS;
	private static final String HELP = Messages.COMMAND_SETROLE_HELP;
	
	private Position pos;
	private LemmingRole role;

	public SetRoleCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP); 
	}
	
	public SetRoleCommand(LemmingRole role, Position pos) {							// Constructor con Parámetros para parse()
		super(NAME, SHORTCUT, DETAILS, HELP); 
		this.role = role;
		this.pos = pos;
	}

	public Command parse(String[] commandWords) throws CommandParseException {		// Parse Llamado por Generator
		
		if (commandWords.length < 1 || !matchCommandName(commandWords[0])) {		// Si comando no existe devuelve null para CommandGenerator
			return null;
		}
		
		if(commandWords.length != 4) {												// Si nº params incorrecto lanza ParseException
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
		
		
		try {																		// Llama a Parse del Factory (Model)
			role = LemmingRoleFactory.parse(commandWords[1]); 						
		} catch (GameParseException e) {											// GameParseE -> CommandParseE
			throw new CommandParseException(Messages.INVALID_COMMAND_PARAMETERS, e);
		}
		
		try {																		// ParseInt
			int row = commandWords[2].charAt(0) -'A';
			int col = Integer.parseInt(commandWords[3]) - 1;
			pos = new Position(row, col);
		} catch (NumberFormatException e) {											// NumberFormatE -> CommandParseE
			throw new CommandParseException(Messages.INVALID_POSITION.formatted
			 		(Messages.POSITION.formatted(commandWords[2], commandWords[3])));
		}
		
		return new SetRoleCommand(role, pos);										// Devuelve SetRoleCommand - Parse éxito

	}
	
	public void execute(GameModel game, GameView view) throws CommandExecuteException {	// Execute de SetRoleC
		
		try {
			game.setRole(role, pos);
			game.update();
			view.showGame();
		} catch (OffBoardException e) {
			throw new CommandExecuteException(Messages.COMMAND_EXECUTE_ERROR, e);
		} catch (RoleParseException e) {
			throw new CommandExecuteException(e.getMessage());
		}
		
	}
	
	public String helpText() {														// Help especial de SetRole con cada roleHelp()
		return super.helpText()+LemmingRoleFactory.roleHelp();
	}
}
