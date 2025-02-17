package tp1.control.commands;

import java.util.Arrays;
import java.util.List;

public class CommandGenerator {

	private static final List<Command> availableCommands 
		= Arrays.asList(
			new NoneCommand(),
			new ResetCommand(),
			new HelpCommand(),
			new ExitCommand()
	);

	public static Command parse(String[] commandWords) {
		for (Command c: availableCommands) {
			Command parsedCommand = c.parse(commandWords);
			if (parsedCommand != null) {
				return parsedCommand;
            }
		}
		return null;
	}
		
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();
		
		for (Command c: availableCommands) {
			commands.append(c.helpText());
		}
		return commands.toString();
	}

}
