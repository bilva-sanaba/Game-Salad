package components;


import javafx.scene.image.Image;

public class SpriteComponent implements IComponent {
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
		return new Image(classPath);
	}
	public IComponent newCopy() {
		return new SpriteComponent(getClassPath());
	}
}