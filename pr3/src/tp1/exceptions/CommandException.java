package tp1.exceptions;

public abstract class CommandException extends Exception {
	
	private static final long serialVersionUID = 1L;
		

	public CommandException(String message) {
		super(message);
	}
	
	public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
		
}
