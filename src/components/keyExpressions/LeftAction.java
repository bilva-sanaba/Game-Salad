package components.keyExpressions;

import java.util.ArrayList;
import java.util.List;

import actions.IAction;
import class_annotations.KeyAction;

import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import entity.IEntity;
import entity.IEntityManager;

@KeyAction
public class LeftAction implements IAction {
	@Override
	public List<IEntity> executeAction(IEntity player, IEntity npc, IEntityManager myEM) {
		LocationComponent lc = (LocationComponent) player.getComponent(ComponentType.Location);
		lc.setX(lc.getX()-5);
		return new ArrayList<IEntity>();
	}

}
