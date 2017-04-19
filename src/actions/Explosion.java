package actions;

import java.util.ArrayList;
import java.util.List;

import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.SpriteComponent;
import entity.Entity;
import entity.IEntity;

public class Explosion implements IAction {
	
	public static final String EXPLOSION_IMAGE = "Feuer46.GIF";

	@Override
	public List<IEntity> executeAction(IEntity e) {
		SpriteComponent sc = (SpriteComponent) e.getComponent(ComponentType.Sprite);
		sc.setClassPath(EXPLOSION_IMAGE);
		e.changed(e);
		//THEY SHOULD MAKE IT SO THAT IMAGEVIEWS ONLY GET IMAGE FROM PATH AGAIN IF PATH HAS CHANGED!!!
		return new ArrayList<IEntity>();
	}

}
