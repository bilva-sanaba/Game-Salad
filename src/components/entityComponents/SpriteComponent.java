package components.entityComponents;


import java.io.File;

import components.IComponent;
import javafx.scene.image.Image;

public class SpriteComponent implements IComponent {
	private static final String FILE_PATH = "file:" + File.separator + System.getProperty("user.dir") + File.separator + "images"+ File.separator;

	private String classPath;
	
	public SpriteComponent(String path){
		classPath= path;
	}
	
	public SpriteComponent() {
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Sprite;
	}

	public void setClassPath(String newPath) {
		classPath = newPath;
	}

	public String getClassPath() {
		return classPath;
	}
	public Image getSprite(){
		return new Image(FILE_PATH + classPath);
	}
	public IComponent newCopy() {
		return new SpriteComponent(getClassPath());
	}
}