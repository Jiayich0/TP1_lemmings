package tp1.control;

import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;

/**
 *  Accepts user input and coordinates the game execution logic
 */
public class Controller {

	private Game game;
	private GameView view;

	public Controller(Game game, GameView view) {
		this.game = game;
		this.view = view;
	}


	/**
	 * Runs the game logic, coordinate Model(game) and View(view)
	 * 
	 */
	public void run() {
		
        view.showWelcome();
        // The main loop that displays the game, asks the user for
        // input, and executes the action.
        view.showGame();

        // Mientras no termine la partida
        while (!game.isFinished()) {
        	String[] words = view.getPrompt();
        	String command = words[0].toUpperCase();
        	
        	if(words.length > 1) {
        		view.showError(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
        		continue;
        	}
        		
        			
            switch (command) {
	            case ("R"):
	            case ("RESET"):
	                game.reset();
	            	//game.update();  //no hace falta hacer un update?
	            	view.showGame();
	                break;

	            case ("H"):
	            case ("HELP"):
	                view.showMessage(Messages.HELP);
	                break;
	
	            case ("E"):
	            case ("EXIT"):
	                game.exit();
	                break;
	                
	            case ("N"):
	            case ("NONE"):
	            case (""):
	            case (" "):
	                game.update(); // Actualizar estadcdo juego
	                view.showGame();
	                break;
	
	            //Si no, mostramos mensaje de error
	            default:
	            	view.showError(Messages.UNKNOWN_COMMAND);
            }
        }

        // Mensaje final del juego
        view.showEndMessage();
    }

}
