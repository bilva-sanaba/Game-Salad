package view.commands;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import view.UtilityFactory;
import view.ViewData;

public class RightClickMenu{
	private ContextMenu contextMenu;
	
	public RightClickMenu(UtilityFactory utilF, ViewData myData){
		contextMenu = new ContextMenu();
		fillMenu(utilF.makeRightClickMenu(myData));
	}
	
	private void fillMenu(List<MenuItem> menuItems) {
		menuItems.stream().forEach(contextMenu.getItems()::add);
	}
	
	public void show(Pane pane, double x, double y){
		contextMenu.show(pane, x, y);
	}
	
	public void hide(){
		contextMenu.hide();
	}

}
