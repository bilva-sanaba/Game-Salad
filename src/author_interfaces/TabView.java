package author_interfaces;

import gameEngine_interface.Sprite;
import javafx.scene.control.TabPane;

public interface TabView {
	public void addSprite(Sprite s);
	public TabPane createTabView(TabData td);
	public TabPane getTabPane();

}