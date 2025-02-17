package tp1.exceptions;

public class OffBoardException extends GameModelException {
	
	private static final long serialVersionUID = 5L;
	
	
	public OffBoardException(String message) {
		super(message);
	}
	
	//public OffBoardException(String message, Throwable cause) {
    //   super(message, cause);
    //}
}
