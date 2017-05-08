package components.entityComponents;

import components.AbstractStringComponent;
import components.IComponent;
import javafx.scene.image.Image;
/**
 * Component which holds information for display on what the image of an entity is
 * @author Bilva
 *
 */

public class SpriteComponent extends AbstractStringComponent implements IComponent {
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
		return new Image(getClass().getClassLoader().getResourceAsStream(getString()));
	}
	
}