package entity.restricted;

import components.ComponentType;
import components.SpriteComponent;
import components.XYComponent;
import entity.Entity;
import gameView.Coordinate;

public class RestrictedEntityFactory {
	public RestrictedEntity createRestrictedEntity(Entity ent){
		return new RestrictedEntity(new Coordinate((XYComponent) ent.getComponent(ComponentType.Location)),
				((SpriteComponent) ent.getComponent(ComponentType.Sprite)).getClassPath());
	}
}
