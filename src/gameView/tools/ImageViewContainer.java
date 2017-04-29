package gameView.tools;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageViewContainer extends ImageView{
	
	private String myPath;
	
	public ImageViewContainer(Image image, String filepath) {
		super(image);
		myPath = filepath;
	}
	
	public String getPath() {
		return myPath;
	}

}
