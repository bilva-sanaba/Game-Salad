package voogasalad.util.paint;

import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

public interface IImageRefiner {
	public Image turnAllWhiteTransparent(Image whiteImage, int threshold);
	public Image turnAllWhiteTransparent(Image whiteImage);
	public Shape getBoundedShape(Image inputImage);
	
	
	
}
