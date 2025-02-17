package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.lemmingRoles.LemmingRoleFactory;
import tp1.view.GameView;
import tp1.view.Messages;

public class SetRoleCommand extends Command{

	// Forman parte de atributos de estado
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

	public Command parse(String[] commandWords) {									// Parse Llamado por Generator
		
		if (commandWords.length == 4 && matchCommandName(commandWords[0])) {
			LemmingRole role = LemmingRoleFactory.parse(commandWords[1]);
			
			if(role != null){
				int row = commandWords[2].toUpperCase().charAt(0) -'A';
				int col = Integer.parseInt(commandWords[3]) - 1;
				Position pos = new Position(col, row);
				return new SetRoleCommand(role, pos);
			}
		}
		return null;
	}
	
	public void execute(GameModel game, GameView view){								// Execute de SetRoleC
		
		if(game.setRole(role, pos)) {
			game.update();
			view.showGame();
		}
		else {
			view.showError(Messages.ROLE_INCORRECT_PARAMETER_NUMBER);
		}
	}
	
	public String helpText() {														// Help especial de SetRole con cada roleHelp()
		return super.helpText()+LemmingRoleFactory.roleHelp();
	}
}
