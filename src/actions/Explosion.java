package actions;

import java.util.ArrayList;
import java.util.List;

import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.SpriteComponent;
import entity.Entity;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameDataFactory;
import gamedata.IRestrictedGameData;

public class Explosion implements IAction {
	
	public static final String EXPLOSION_IMAGE = "Feuer46.GIF";

	@Override
	public IRestrictedGameData executeAction(IEntity e, IEntity e2, IEntityManager myEM, IRestrictedGameData currentGameData) {
		ImagePropertiesComponent ic = (ImagePropertiesComponent) e.getComponent(ComponentType.ImageProperties);
		GameDataFactory gdf = new GameDataFactory();

		return gdf.blankEntityData(currentGameData);
	}

}