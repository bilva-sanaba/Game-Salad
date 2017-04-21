package actions;

import java.util.List;

import components.LocationComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.ObjectCreationComponent;
import entity.IEntity;
import entity.IEntityManager;

public class ShootAction implements IAction {

	@Override
	public List<IEntity> executeAction(IEntity player, IEntity npc, IEntityManager myEM) {
		PowerupCreation p = new PowerupCreation();
		ObjectCreationComponent occ = (ObjectCreationComponent) player.getComponent(ComponentType.ObjectCreation);
		npc = occ.checkCreationEffect(); 
		LocationComponent lcplayer= (LocationComponent) player.getComponent(ComponentType.Location);
		LocationComponent lcnpc= (LocationComponent) npc.getComponent(ComponentType.Location);
		lcnpc.setX(lcplayer.getX()+60);
		lcnpc.setY(lcplayer.getY());
		return p.executeAction(npc, player, myEM);
	}

}
