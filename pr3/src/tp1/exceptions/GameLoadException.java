package tp1.exceptions;

public class GameLoadException extends GameModelException {
	
	private static final long serialVersionUID = 9L;
	
	public GameLoadException(String message) {
		super(message);
	}
}
