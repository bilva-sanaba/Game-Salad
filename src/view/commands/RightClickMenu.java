//This entire file is part of my masterpiece
//Jack Bloomfeld
package view.commands;

import java.util.List;

import entity.Entity;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import view.UtilityFactory;
import view.ViewData;

/**
 * This class is part of my masterpiece because it demonstrates a lot of key design principles that I thought were very important
 * for the design of this project. First of all, it makes use of an interface that designates all of the necessary public methods
 * so that when it is instantiated inside of the GridView class, the interface can be instantiated so that only the appropriate methods
 * can be accessed. Additionally, the design of this menu is very extensible because of how reflection is used to determine the
 * event. The EventFactory class reflects the name of the command from a properties file to create an instance of the appropriate
 * command, and then the execute() method of that command object is called. This design makes it very easy to add new commands
 * to the menu by simply adding the name of the command to the properties file, and then creating that command class and defining
 * the necessary execute() method. The extensibility of this component along with the encapsulation of the execute() methods
 * of the different commands make it a well designed component.
 * @author Jack
 *
 */

public class RightClickMenu implements IRightClickMenu{
	private static final String MENU_ERROR = "Error displaying menu";
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
	}
	
	public void hide(){
		try{
			currentMenu.hide();
		}catch (Exception e){
			Alert error = new Alert(AlertType.ERROR);
			error.setContentText(MENU_ERROR);
			error.show();
		}
	}

	public boolean isShowing() {
		boolean show;
		try {
			show = currentMenu.isShowing();
		} catch (Exception e) {
			show = false;
			Alert error = new Alert(AlertType.ERROR);
			error.setContentText(MENU_ERROR);
			error.show();
		}
		return show;
	}

}
