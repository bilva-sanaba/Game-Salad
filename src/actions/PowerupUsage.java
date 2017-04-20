package actions;

import java.util.ArrayList;
import java.util.List;

import components.entityComponents.ComponentType;
import components.entityComponents.SpriteComponent;
import entity.IEntity;
import entity.IEntityManager;

public class PowerupUsage implements IAction{

	@Override
	public List<IEntity> executeAction(IEntity player, IEntity npc, IEntityManager myEM) {
		System.out.println("remove this powerup");
		SpriteComponent npcsc = (SpriteComponent) npc.getComponent(new SpriteComponent());
		myEM.getEntities().remove(npc);
		((SpriteComponent) npc.getComponent(new SpriteComponent())).setClassPath("");
		npc.changed(null);
		
		List<IEntity> entities = new ArrayList<IEntity>();
		return entities;
	}

}
