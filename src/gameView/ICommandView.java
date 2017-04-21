package gameView;

import javafx.scene.Scene;
import gameView.tools.DisplayManager;
import gameView.tools.UserData;

public interface ICommandView {

	public Scene getScene();
	public void runGame();
	public void loadGame(String filepath);
	public void restart();
	public void saveGame();
	public void makeGame();
	public DisplayManager getComponents();
	public void pauseGame();
	public String getInstructions();
	public void addUser(UserData user);
	
}
