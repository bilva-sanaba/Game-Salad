package gameView.observers;

import javafx.scene.image.ImageView;

public class ImageConfig {
	
	private String path;
	private ImageView imageView;
	
	public ImageConfig(ImageView imageView, String path){
		this.imageView = imageView;
		this.path = path;
	}
	
	public String getPath(){
		return path;
	}
	public ImageView getImageView(){
		return imageView;
	}
	public void setPath(String newPath){
		path = newPath;
	}
}
