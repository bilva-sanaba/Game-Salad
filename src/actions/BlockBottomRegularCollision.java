package actions;

import java.util.ArrayList;
import java.util.List;

import class_annotations.BottomAction;
import components.entityComponents.AccelerationComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.LabelComponent;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameDataFactory;
import gamedata.IRestrictedGameData;

@BottomAction()
public class BlockBottomRegularCollision implements IAction {
	
	@Override
	public IRestrictedGameData executeAction(IEntity e, IEntity e2, IEntityManager myEM, IRestrictedGameData currentGameData) {
		VelocityComponent velo = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		velo.setY(-1*velo.getY());
		GameDataFactory gdf = new GameDataFactory();

		return gdf.blankEntityData(currentGameData);
	}
}
