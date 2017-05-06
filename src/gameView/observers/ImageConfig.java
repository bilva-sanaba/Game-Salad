//This entire file is part of my masterpiece
//JACOB WEISS
package gameView.observers;

import javafx.scene.image.ImageView;
/**
 * Proxy design ImageConfig object stores imageview and string file path so that updated image's files can be compared
 * and not changed if the same. This allows for GIFs which are moving to appear as GIFs
 * Otherwise they would be reloaded every step and no animation would be witnessed by game player
 * Objects from the entity manager observer are converted to a map containing ID to this object for the WorldAnimator to update
 * @author Jacob
 *
 */
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
