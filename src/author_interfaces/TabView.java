package author_interfaces;

import author_interfaces.GUIComponent;
import gameEngine_interface.Entity;
import javafx.scene.control.TabPane;

public interface TabView extends GUIComponent{
	public void addSprite(Entity s);
	public TabPane createTabView(TabData td);
	public TabPane getTabPane();

}