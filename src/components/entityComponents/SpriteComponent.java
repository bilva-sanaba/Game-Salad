package components.entityComponents;


import components.AbstractOneParameterComponent;
import components.AbstractStringComponent;
import components.IComponent;
import javafx.scene.image.Image;

public class SpriteComponent extends AbstractOneParameterComponent<String> implements IComponent {
	private String myPath;
	public SpriteComponent(String path){
		super(path);
		myPath=path;
		
	}
	
	public SpriteComponent() {
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Sprite;
	}

	
	public Image getSprite(){
		System.out.println(getString().getClass().getName());
		return new Image(getClass().getClassLoader().getResourceAsStream(getString().toString()));
	}
	
}