package actions;

import java.util.ArrayList;
import java.util.List;

import components.entityComponents.AccelerationComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameDataFactory;
import gamedata.IRestrictedGameData;

public class BounceOffBlockSide implements IAction {

	public BounceOffBlockSide() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IRestrictedGameData executeAction(IEntity e,IEntity e2, IEntityManager myEM, IRestrictedGameData currentGameData) {
		AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
		VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		ac.setX(-1*ac.getX()); //UNSURE IF ACCELERATION SHOULD JUST REVERSED
		ac.setY(1*ac.getY());
		GameDataFactory gdf = new GameDataFactory();
		return gdf.blankEntityData(currentGameData);

	}

}
