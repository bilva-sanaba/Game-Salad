package voogasalad.util.paint;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class Toolbar implements IDrawingToolbar {
	private ToolBar myToolbar;
	private DrawingTool myDrawer;

	private ColorPicker myColorPicker = new ColorPicker(Color.BLACK);
	private TextField penSizeButton = new TextField();
	private IDrawingToolChooser myChooser;


	public Toolbar(IDrawingToolChooser t){
		myChooser = t;
		myDrawer=myChooser.getDrawingTool();
		addColorPicker();
		addPenSizeButton();
		myToolbar = new ToolBar(
				myColorPicker,
				new Separator(),
				penSizeButton,   
				new Separator(),
				addDrawingToolChooser());
	}
	
	private void addColorPicker(){
		myColorPicker.setValue(Color.BLACK);
		myColorPicker.setOnAction(e -> changeColor());
	}
	
	private void changeColor(){
		myChooser.setColor(myColorPicker.getValue());
	}
	
	private void addPenSizeButton(){
		((TextField) penSizeButton).setPromptText("Enter Pen Size");
		((TextField) penSizeButton).setOnAction(e -> {
			try{
				if (myDrawer instanceof Pen)
				((Pen) myDrawer).setWidth(Double.parseDouble(penSizeButton.getText()));
				((TextField) penSizeButton).clear();
			}catch(IllegalArgumentException y){

			}
			catch(NullPointerException i){}
		});
	}
	
	private HBox addDrawingToolChooser(){
		ToggleGroup group = new ToggleGroup();
		HBox hbox = new HBox(15);
		
		RadioButton myPen = new RadioButton("Pen");
		myPen.setSelected(true);
		myPen.setToggleGroup(group);
		hbox.getChildren().add(myPen);
		
		RadioButton myCircle = new RadioButton("Cricle");
		myCircle.setSelected(false);
		myCircle.setToggleGroup(group);
		hbox.getChildren().add(myCircle);
		
		RadioButton myRectangle = new RadioButton("Rectangle");
		myRectangle.setSelected(false);
		myRectangle.setToggleGroup(group);
		hbox.getChildren().add(myRectangle);
		
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> arg0, Toggle oldTog, Toggle newTog) {
				if (newTog.equals(myPen)){
					myChooser.setDrawingTool(DrawingToolType.Pen);
				} else if (newTog.equals(myCircle)){
					myChooser.setDrawingTool(DrawingToolType.Cirlce);
				} else{
					myChooser.setDrawingTool(DrawingToolType.Rectangle);
				}
			}
		});
		return hbox;
	}
	
	

	
	@Override
	public Region getRegion() {
		return myToolbar;
	}
}
