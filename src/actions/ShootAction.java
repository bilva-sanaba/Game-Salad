package actions;

import java.util.ArrayList;
import java.util.List;


import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import components.entityComponents.ObjectCreationComponent;
import entity.IEntity;
import entity.IEntityManager;

public class ShootAction implements IAction {

	@Override
	public List<IEntity> executeAction(IEntity player, IEntity npc, IEntityManager myEM) {
		ObjectCreationComponent occ = (ObjectCreationComponent) player.getComponent(ComponentType.ObjectCreation);
		npc = occ.checkCreationEffect(); 
		LocationComponent lcplayer= (LocationComponent) player.getComponent(ComponentType.Location);
		LocationComponent lcnpc= (LocationComponent) npc.getComponent(ComponentType.Location);
		lcnpc.setX(lcplayer.getX()+60);
		lcnpc.setY(lcplayer.getY());
		List<IEntity> entities = new ArrayList<IEntity>();
		
		if (npc!=null){
			entities.add(npc);
		}
		return entities;
	}

}
