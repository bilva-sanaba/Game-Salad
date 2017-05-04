package view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import entity.Entity;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * 
 * buildButton(String, String) credit to Duvall
 * 
 * @author Jonathan
 * @author Jack
 *
 */
public class UtilityFactory {

	public static final String DEFAULT_RESOURCE_PACKAGE = "resources" + File.separator;
	public static final String SPLIT_REGEX = ", ";
	private ResourceBundle imagesResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "images");
	private ResourceBundle menuResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "menu");

	// private ResourceBundle entityPresets =
	// ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "entities");
	private ResourceBundle myResources;

	public UtilityFactory(String language) {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
	}

	public Tab buildTab(String label, Boolean closable) {
		Tab myTab = new Tab();
		myTab.setText(myResources.getString(label));
		myTab.setClosable(closable);
		return myTab;
	}

	public Button buildButton(String property, String eventname, String imageFile, ViewData data) {
		ResourceBundle imagesResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + imageFile);

		// represent all supported image suffixes
		final String IMAGEFILE_SUFFIXES = String.format(".*\\.(%s)", String.join("|", ImageIO.getReaderFileSuffixes()));

		Button result = new Button();
		result.setTooltip(new Tooltip(property));
		String label = imagesResources.getString(property);
		if (label.matches(IMAGEFILE_SUFFIXES)) {
			result.setGraphic(new ImageView(new Image(DEFAULT_RESOURCE_PACKAGE + label)));
		} else {
			result.setText(label);
		}
		EventFactory evfac = new EventFactory(this);
		result.setOnAction(e -> {
			try {
				evfac.getEvent(eventname, data).event();
			} catch (Exception e1) {
				// alert
			}
		});
		return result;
	}

	public Button buildButton(String property, String eventname, ViewData data) {
		return buildButton(property, eventname, "images", data);
	}

	public Button buildButton(String property, String eventname) {
		return buildButton(property, eventname, "images", null);
	}

	public Button buildButton(String string, EventHandler eventname) {
		Button result = new Button();
		result.setText(myResources.getString(string));
		result.setOnAction(eventname);
		return result;
	}

	public MenuItem builtMenuItem(String name, EventHandler<ActionEvent> event) {
		MenuItem myMenuItem = new MenuItem(myResources.getString(name));
		myMenuItem.setOnAction(event);
		return myMenuItem;
	}

	private RadioButton buildRadioButton(String property, boolean selected, ToggleGroup group, VBox vbox) {
		RadioButton myButton = new RadioButton(property);
		myButton.setSelected(selected);
		myButton.setToggleGroup(group);
		myButton.setUserData(myResources.getString(property + "RadioButton").split(SPLIT_REGEX));
		vbox.getChildren().add(myButton);

		return myButton;
	}

	public List<Button> makeToolBarButtons(ViewData data) {
		List<Button> toolButtons = new ArrayList<Button>();
		String[] names = imagesResources.getString("IconNames").split(SPLIT_REGEX);
		String[] events = imagesResources.getString("EventNames").split(SPLIT_REGEX);
		for (int i = 0; i < names.length; i++) {
			toolButtons.add(buildButton(names[i], events[i], data));
		}
		return toolButtons;
	}

	public List<MenuItem> makeRightClickMenu(ViewData data, Entity entity, double x, double y) {
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		String[] names = menuResources.getString("MenuItemNames").split(SPLIT_REGEX);
		String[] events = menuResources.getString("MenuEventNames").split(SPLIT_REGEX);
		for (int i = 0; i < names.length; i++) {
			menuItems.add(buildMenuItem(names[i], events[i], data, entity, x, y));
		}
		return menuItems;
	}

	private MenuItem buildMenuItem(String property, String eventname, ViewData data, Entity entity, double x,
			double y) {
		MenuItem menuItem = new MenuItem();
		menuItem.setText(property);
		EventFactory evfac = new EventFactory(this);
		menuItem.setOnAction(e -> {
			try {
				System.out.println(eventname);
				evfac.getRightClickEvent(eventname, this, data, entity, x, y).execute();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		return menuItem;
	}

	public Integer convertToInt(Double d) {
		Integer i = d.intValue();
		return i;
	}

	public ToggleGroup buildRadioButtonGroup(String string, Pane root) {
		ToggleGroup group = new ToggleGroup();
		VBox vbox = new VBox();
		String[] radioButton = myResources.getString(string).split(SPLIT_REGEX);
		Integer buttonNum = Integer.parseInt(radioButton[0]);
		for (int i = 1; i <= buttonNum; i++) {
			boolean marked = radioButton[i + buttonNum].trim().equals("true"); // sees
																				// if
																				// button
																				// should
																				// be
																				// initally
																				// marked
			buildRadioButton(radioButton[i], marked, group, vbox);
		}
		root.getChildren().add(vbox);
		return group;
	}

	public HBox buildSlider(String compName, ChangeListener<? super Number> listener) {
		HBox myBox = new HBox();
		String[] sliderProp = myResources.getString(compName + "Slider").split(SPLIT_REGEX);
		Text myLabel = new Text(sliderProp[0]);
		Slider mySlider = new Slider(Double.parseDouble(sliderProp[1]), Double.parseDouble(sliderProp[2]),
				Double.parseDouble(sliderProp[3]));
		myBox.getChildren().add(myLabel);
		myBox.getChildren().add(mySlider);
		mySlider.setSnapToTicks(sliderProp[5].equals("true"));
		mySlider.setMajorTickUnit(Double.parseDouble(sliderProp[6]));
		mySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			mySlider.setValue(newValue.doubleValue());
			myLabel.setText(String.format(sliderProp[4], newValue.doubleValue()));
		});
		mySlider.valueProperty().addListener(listener);
		return myBox;
	}

	public TextField buildTextField(String label) {
		String[] textAreaProp = myResources.getString(label + "Area").split(SPLIT_REGEX);
		TextField tA = new TextField(textAreaProp[0]);
		tA.setPromptText(textAreaProp[0]);
		return tA;
	}

	public VBox buildVBox(Node... nodes) {
		VBox vb = new VBox();
		vb.getChildren().addAll(nodes);
		return vb;
	}

	public HBox buildHBox(Node... nodes) {
		HBox hb = new HBox();
		hb.getChildren().addAll(nodes);
		return hb;
	}
	
	public Pane buildGrid(int width, int height, String style){
		Pane myGrid = new Pane();
		myGrid.setPrefSize(width, height);
		myGrid.getStyleClass().add(style);
		return myGrid;
	}

	public <T> ListView<T> buildListView(List<T> list) {
		ObservableList<T> obslist = FXCollections.observableArrayList(list);
		return new ListView<T>(obslist);
	}

	public <T> ListView<T> buildListView(T[] list) {
		ObservableList<T> obslist = FXCollections.observableArrayList(list);
		return new ListView<T>(obslist);
	}

	public void setPresets(ObservableList<Entity> list) {

	}

	public String getText(String act) {
		String rec = act;
		try{
			rec = myResources.getString(act.split(" ")[1]);
		}catch(Exception e){
			rec = myResources.getString(act);
		}
		return rec;
	}
	
	
}
