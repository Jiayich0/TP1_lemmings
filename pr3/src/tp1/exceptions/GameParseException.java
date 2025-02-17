package tp1.exceptions;

public class GameParseException extends GameModelException {
	
	private static final long serialVersionUID = 6L;
	
	
	public GameParseException(String message) {
		super(message);
	}
	
	//public GameParseException(String message, Throwable cause) {
    //   super(message, cause);
    //}
}
