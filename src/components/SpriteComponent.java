package components;

import javafx.scene.image.Image;

public class SpriteComponent implements IComponent {
	private String classPath;
	private Image sprite;
	public SpriteComponent(String path){
		classPath= path;
		sprite = new Image(path);
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
