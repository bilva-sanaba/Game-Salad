package gameView.gameScreen;

import gameView.displayComponents.UIDisplayComponent;


/**
 * Interface for accepting HUD components
 * @author Henry
 *
 */
public interface IGameScreenDisplays {
	
	
	/**
	 * Add a component
	 * @param toAdd - component to add
	 */
	public void addComponent(UIDisplayComponent toAdd);
	
	/**
	 * Remove a component
	 * @param toRemove - component to remove
	 */
	public void removeComponent(UIDisplayComponent toRemove);
	
}
