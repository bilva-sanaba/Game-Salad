package actions;

import java.util.List;

import components.entityComponents.ComponentType;
import components.entityComponents.SpriteComponent;
import entity.IEntity;

public class PowerupUsage implements IAction{

	@Override
	public List<IEntity> executeAction(IEntity player, IEntity npc) {
		System.out.println("remove this powerup");
		SpriteComponent npcsc = (SpriteComponent) npc.getComponent(new SpriteComponent());
		npcsc.setClassPath("");
		npc.changed(null);
		// TODO Auto-generated method stub
		return null;
	}

}
