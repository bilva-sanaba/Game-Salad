package voogasalad.util.paint;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Region;

public class Toolbar implements IDrawingToolbar {
	private ToolBar myToolbar;
	private Pen myPen;
	private ColorPicker myColorPicker = new ColorPicker();
	private TextField penSizeButton = new TextField();

	public Toolbar(Pen p){
		myPen=p;
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
				myPen.setWidth(Double.parseDouble(penSizeButton.getText()));
				((TextField) penSizeButton).clear();
			}catch(IllegalArgumentException y){

			}
			catch(NullPointerException i){}
		});
	}

	@Override
	public Region getToolbar() {
		return myToolbar;
	}


}
