package components.keyExpressions;

import java.util.ArrayList;
import java.util.List;

import actions.IAction;
import class_annotations.KeyAction;
import components.LocationComponent;
import components.entityComponents.ComponentType;
import entity.IEntity;
import entity.IEntityManager;
@KeyAction()
public class RightAction implements IAction{

	@Override
	public List<IEntity> executeAction(IEntity player, IEntity npc, IEntityManager myEM) {
		LocationComponent lc = (LocationComponent) player.getComponent(ComponentType.Location);
		lc.setX(lc.getX()+3);
		return new ArrayList<IEntity>();
	}

}
