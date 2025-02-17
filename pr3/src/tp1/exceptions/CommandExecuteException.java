package tp1.exceptions;

public class CommandExecuteException extends CommandException {
	
	private static final long serialVersionUID = 3L;
	
	
	public CommandExecuteException(String message) {
		super(message);
	}
	
	public CommandExecuteException(String message, Throwable cause) {
        super(message, cause);
    }
}
