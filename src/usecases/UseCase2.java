package usecases;
/**
 * Example code for "User saves the game" use case
 * @author jwei528
 *
 */
public class UseCase2 {
	
	/**
	 * User presses save button on Gui
	 */
	saveButton.isClicked();
	
	/**
	 * View calls save
	 */
	Controller.save();
	
	/**
	 * loop through all Map positions in the Controller
	 */
	for(UIImageProperty x : Map){
		DataInterface.saveImage(x);
	}
}
