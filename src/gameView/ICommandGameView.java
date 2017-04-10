package gameView;

import gameView.tools.DisplayManager;

import java.util.Collection;

public interface ICommandGameView extends ICommandView {
	
	public DisplayManager getComponents();
	
	public void pauseGame();

}
