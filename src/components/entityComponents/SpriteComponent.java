package components.entityComponents;


import components.AbstractOneParameterComponent;
import components.IComponent;
import javafx.scene.image.Image;

public class SpriteComponent extends AbstractOneParameterComponent<String> implements IComponent {	
	public SpriteComponent(String path){
		super(path);
	}
	
	public SpriteComponent() {
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Sprite;
	}

	
	public Image getSprite(){
		return new Image(getClass().getClassLoader().getResourceAsStream(getObject()));
	}
}