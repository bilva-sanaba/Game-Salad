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

@BottomAction()
public class BlockBottomRegularCollision implements IAction {
	
	@Override
	public List<IEntity> executeAction(IEntity e, IEntity e2, IEntityManager myEM) {
		VelocityComponent velo = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		velo.setY(-1*velo.getY());
		return new ArrayList<IEntity>();

	}
}
