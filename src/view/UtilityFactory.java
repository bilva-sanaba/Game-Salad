package view;

import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * buildButton(String, String) credit to Duvall
 * 
 * @author Jonathan
 *
 */
public class UtilityFactory {
	
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	private ResourceBundle myResources;
	
	public UtilityFactory(String language){
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
	}
	
	public Tab buildTab(String label, Boolean closable){
		Tab myTab = new Tab();
		myTab.setText(label);
		myTab.setClosable(closable);
		return myTab;
	}
	
	public Button buildButton(String property, String eventname){
		ResourceBundle imagesResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "images");

		// represent all supported image suffixes
        final String IMAGEFILE_SUFFIXES =
                String.format(".*\\.(%s)", String.join("|", ImageIO.getReaderFileSuffixes()));

        Button result = new Button();
        String label = imagesResources.getString(property);
        if (label.matches(IMAGEFILE_SUFFIXES)) {
            result.setGraphic(new ImageView(
                                  new Image(DEFAULT_RESOURCE_PACKAGE + label)));
        } else {
            result.setText(label);
        }
//        EventFactory evfac = new EventFactory();
//        EventHandler handler = evfac.getEvent(eventname);
//        result.setOnAction(handler);
        return result;
	}
	
	public MenuItem builtMenuItem(String name, EventHandler<ActionEvent> event){
		MenuItem myMenuItem = new MenuItem(name);
		myMenuItem.setOnAction(event);
		return myMenuItem;
	}

	public Button buildButton(String string, EventHandler eventname) {
        Button result = new Button();
        result.setText(string);
        result.setOnAction(eventname);
        return result;
	}

}
