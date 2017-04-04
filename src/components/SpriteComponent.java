package components;

import javafx.scene.image.Image;
/**
 * Component used for setting the image displayed for an entity
 * @author Bilva
 *
 */
public class SpriteComponent implements IComponent {
	private String classPath;
	private Image sprite;
	public SpriteComponent(String path){
		classPath= path;
	}
	public SpriteComponent(){}
	@Override
	public ComponentType getComponentType() {
		return ComponentType.Sprite;
	}
	public void setClassPath(String newPath){
		classPath = newPath;
	}
	public String getClassPath(){
		return classPath;
	}
	public Image getSprite(){
		return sprite;
	}
	public void setSprite(Image img){
		sprite = img;
	}
}
