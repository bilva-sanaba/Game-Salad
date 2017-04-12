package entity.restricted;

import components.entityComponents.ComponentType;
import components.entityComponents.SpriteComponent;
import components.entityComponents.XYComponent;
import entity.Entity;
import entity.IEntity;
import gameView.Coordinate;

public class RestrictedEntityFactory {
	public RestrictedEntity createRestrictedEntity(IEntity ent){
		return new RestrictedEntity(ent.getID(), new Coordinate((XYComponent) ent.getComponent(ComponentType.Location)),
				((SpriteComponent) ent.getComponent(ComponentType.Sprite)).getClassPath());
	}
}
