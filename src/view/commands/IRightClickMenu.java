//This entire file is part of my masterpiece
//Jack Bloomfeld
package view.commands;

import entity.Entity;
import javafx.scene.layout.Pane;

/**
 * Interface for the Right Click Menu
 * @author Jack
 *
 */

public interface IRightClickMenu {
	public void show(Pane pane, Entity e, double x, double y, double placex, double placey);
	public void hide();
	public boolean isShowing();
}
