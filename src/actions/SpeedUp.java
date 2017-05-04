package actions;

import java.util.HashMap;
import java.util.Map;

import class_annotations.BottomAction;
import class_annotations.TopAction;
import components.entityComponents.ComponentType;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

@TopAction()
@BottomAction()

public class SpeedUp extends AbstractAction implements IAction {

	private Map<IEntity, Double> previouslySeenEntities = new HashMap<IEntity, Double>();
	
	public static final double SPEED_UP_FACTOR = 2;
	

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		VelocityComponent veloOther = (VelocityComponent) other.getComponent(ComponentType.Velocity);
		if (!previouslySeenEntities.containsKey(other) || (previouslySeenEntities.get(other) != null && Math.abs(veloOther.getX()) < 
				SPEED_UP_FACTOR*previouslySeenEntities.get(other))) {
			previouslySeenEntities.put(other, Math.abs(veloOther.getX()));
			veloOther.setX(veloOther.getX()*SPEED_UP_FACTOR);
		}
		return getGameDataFactory().blankEntityData(currentGameData);

	}

}
