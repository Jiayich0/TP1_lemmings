package tp1.control.commands;

import java.util.Arrays;
import java.util.List;
import tp1.exceptions.CommandParseException;
import tp1.view.Messages;

public class CommandGenerator {

	private static final List<Command> availableCommands							// Lista de Comandos Disponibles
		= Arrays.asList(
			new SetRoleCommand(),
			new NoneCommand(),
			new ResetCommand(),
			new LoadCommand(),
			new HelpCommand(),
			new ExitCommand()
	);

	public static Command parse(String[] commandWords) throws CommandParseException { // Parse(): Recorre la Lista de Comandos
		for (Command c: availableCommands) {										// Si coincide nombre: devuelve Clase
			Command parsedCommand = c.parse(commandWords);							// Si no: lanza Exception
			if (parsedCommand != null) {
				return parsedCommand;
            }
		}
		throw new CommandParseException(Messages.UNKNOWN_COMMAND.formatted(commandWords[0]));
		
	}
		
	public static String commandHelp() {											// Muestra 'help' de todos los Comandos
		StringBuilder commands = new StringBuilder();
		
		for (Command c: availableCommands) {
			commands.append(c.helpText());
		}
		return commands.toString();
	}

}
