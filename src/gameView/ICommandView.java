package gameView;

import gameView.tools.DisplayManager;

public interface ICommandView {

	public void runGame();
	public void loadGame(String filepath);
	public void restart();
	public void saveGame();
	public void makeGame();
	public DisplayManager getComponents();
	public void pauseGame();
	public String getInstructions();
}
