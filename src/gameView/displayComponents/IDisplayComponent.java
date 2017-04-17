package gameView.displayComponents;

import gameView.tools.DisplayEnum;
import javafx.geometry.Dimension2D;
import javafx.scene.layout.Region;

public interface IDisplayComponent {

	/**
	 * Get the name of the component for reflection
	 * @return
	 */
	public String getName();
	
	/**
	 * Get Dimensions of the display of the component
	 * @return
	 */
	public Dimension2D getSize();
	
	/**
	 * Get the display of the component
	 * @return
	 */
	public Region getDisplay();
	
	/**
	 * Get Position of the component from DisplayEnum
	 * @return
	 */
	public DisplayEnum getPos();
}
