package components.entityComponents;


import java.io.File;

import components.AComponent;
import components.AbstractStringComponent;
import components.IComponent;
import javafx.scene.image.Image;

public class SpriteComponent extends AbstractStringComponent implements IComponent {	
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
		return new Image(getClass().getClassLoader().getResourceAsStream(getString()));
	}
}