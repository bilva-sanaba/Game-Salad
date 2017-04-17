package voogasalad.util.paint;

import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class Toolbar {
private Region myToolbar;
private Pen myPen;

public Toolbar(Pen p){
	myPen=p;
	myToolbar = new VBox();
}

}
