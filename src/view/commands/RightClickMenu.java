package view.commands;

import java.util.List;

import entity.Entity;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import view.UtilityFactory;
import view.ViewData;

public class RightClickMenu {
	private boolean isShowing;
	private ViewData myData;
	private UtilityFactory utilF;
	private ContextMenu currentMenu;

	public RightClickMenu(UtilityFactory utilIn, ViewData dataIn) {
		utilF = utilIn;
		myData = dataIn;
	}

	private void fillMenu(List<MenuItem> menuItems, ContextMenu menu) {
		menuItems.stream().forEach(menu.getItems()::add);
	}

	public void show(Pane pane, Entity e, double x, double y, double placex, double placey) {
		ContextMenu newMenu = new ContextMenu();
		fillMenu(utilF.makeRightClickMenu(myData, e, placex, placey), newMenu);
		currentMenu = newMenu;
		currentMenu.show(pane, x, y);
		isShowing = true;
	}

	public void hide() {
		currentMenu.hide();
	}
<<<<<<< HEAD

	public boolean isShowing() {
		return isShowing;
=======
	
	public boolean isShowing(){
		boolean show;
		try{
			show =  currentMenu.isShowing();
		}catch (Exception e){
			show =  false;
		}
		return show;
>>>>>>> 6a1ccbdc9f4aaf7a1eee3db726749b72174821b3
	}

}
