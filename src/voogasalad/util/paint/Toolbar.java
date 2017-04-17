package voogasalad.util.paint;

import java.util.ResourceBundle;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class Toolbar implements IDrawingToolbar {
	private ToolBar myToolbar;
	private DrawingTool myPen;
	private ResourceBundle myResources = ResourceBundle.getBundle("voogasalad.util.paint.paintresourse");;
	private ColorPicker myColorPicker = new ColorPicker();
	private TextField penSizeButton = new TextField();
    public static final String SPLIT_REGEX = ", ";


	public Toolbar(IDrawingToolChooser t){
		myPen=t.getDrawingTool();
		addColorPicker();
		addPenSizeButton();
		myToolbar = new ToolBar(
				myColorPicker,
				new Separator(),
				penSizeButton   
				);
	}
	
	private void addColorPicker(){
		myColorPicker.setOnAction(e -> myPen.setColor(myColorPicker.getValue()));
	}
	
	private void addPenSizeButton(){
		((TextField) penSizeButton).setPromptText("Enter Pen Size");
		((TextField) penSizeButton).setOnAction(e -> {
			try{
				if (myPen instanceof Pen)
				((Pen) myPen).setWidth(Double.parseDouble(penSizeButton.getText()));
				((TextField) penSizeButton).clear();
			}catch(IllegalArgumentException y){

			}
			catch(NullPointerException i){}
		});
	}
	
	private HBox addDrawingToolChooser(){
		ToggleGroup group = new ToggleGroup();
		HBox hbox = new HBox();
		String[] radioButton = myResources.getString("SelectDrawingTools").split(SPLIT_REGEX);
			boolean marked = radioButton[i+buttonNum].trim().equals("true"); // sees if button should be initally marked
			buildRadioButton(radioButton[i], marked, group, hbox);
		
		return hbox;
	}
	
	private RadioButton buildRadioButton(String property, boolean selected, ToggleGroup group, HBox hbox){
		RadioButton myButton = new RadioButton(property);
		myButton.setSelected(selected);
		myButton.setToggleGroup(group);
		myButton.setUserData(myResources.getString(property+"RadioButton").split(SPLIT_REGEX));
		hbox.getChildren().add(myButton);
		return myButton;
	}
	
	
	@Override
	public Region getRegion() {
		return myToolbar;
	}
}
