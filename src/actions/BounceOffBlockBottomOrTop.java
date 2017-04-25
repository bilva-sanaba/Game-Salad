package actions;

import java.util.ArrayList;
import java.util.List;

import components.entityComponents.ComponentType;
import components.entityComponents.VelocityComponent;
import entity.Entity;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameDataFactory;
import gamedata.IRestrictedGameData;

public class BounceOffBlockBottomOrTop implements IAction{

	public BounceOffBlockBottomOrTop() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IRestrictedGameData executeAction(IEntity e,IEntity e2, IEntityManager myEM, IRestrictedGameData currentGameData) {
		VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		vc.setY(0);
		//Does anything need to be done about acceleration?
		GameDataFactory gdf = new GameDataFactory();
		return gdf.blankEntityData(currentGameData);

	}

}
