package gameView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UIImageModel {

	private ImageView myImageView;
	private Coordinate myLocation;

	public UIImageModel(Coordinate coord, String imagepath) {
		myLocation = coord;
		myImageView = new ImageView();
		setImage(imagepath);
	}

	public ImageView getImage() {
		return myImageView;
	}

	public Coordinate getLocation() {
		return myLocation;
	}

	public void updateLocation(Coordinate coord) {
		myLocation = coord;
	}

	public void updateImage(String imagepath) {
		setImage(imagepath);
	}

	private void setImage(String filepath) {
		Image image = new Image(getClass().getClassLoader()
				.getResourceAsStream(filepath));
		myImageView.setImage(image);
	}
}
