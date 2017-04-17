package voogasalad.util.paint;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Region;

public class Toolbar implements IDrawingToolbar {
	private ToolBar myToolbar;
	private DrawingTool myPen;
	private ColorPicker myColorPicker = new ColorPicker();
	private TextField penSizeButton = new TextField();

	public Toolbar(DrawingTool t){
		myPen=t;
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
	private void addDrawingToolChooser(){
		
	}
	@Override
	public Region getRegion() {
		return myToolbar;
	}
}
