package usecases;

public class UseCase2 {
	
	//User presses save button on Gui
	saveButton.isClicked();
	
	//View calls save
	Controller.save();
	
	//loop through all Map positions in the Controller
	for(UIImageProperty x : Map){
		DataInterface.saveImage(x);
	}
}
