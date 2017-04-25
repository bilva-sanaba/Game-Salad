package actions;

import java.util.ArrayList;
import java.util.List;

import class_annotations.TopAction;
import components.entityComponents.ComponentType;

import components.entityComponents.VelocityComponent;

import entity.IEntity;
import entity.IEntityManager;

@TopAction()
public class BounceOffTop implements IAction {
	public static final double VELOCITY_REVERSE = -1;
	public static final double BOUNCE_FACTOR = 0.66;

	

	@Override

	public List<IEntity> executeAction(IEntity player, IEntity npc, IEntityManager myEM) {

		VelocityComponent vc = (VelocityComponent) player.getComponent(ComponentType.Velocity);
		
		if (vc.getY()>0 && vc.getY()<0.25) {
			vc.setY(0);
			
		} else {
			if (vc.getY()>0) {
				vc.setY(vc.getY()*VELOCITY_REVERSE*BOUNCE_FACTOR);
			} 
		}
		
		
		return new ArrayList<IEntity>();
	}

}
