package voogasalad.util.paint;

import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public interface ICanvas {

	GraphicsContext getGraphicsContext2D();

	double getHeight();

	double getWidth();

	Node getRegion();

	Image getImage();

}
