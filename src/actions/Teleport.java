package actions;

import java.util.ArrayList;
import java.util.List;


import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import entity.IEntity;
import entity.IEntityManager;

public class Teleport implements IAction {
	private double teleportXLocation;
	private double teleportYLocation;
	
	public Teleport(double newX, double newY) {
		teleportXLocation = newX;
		teleportYLocation = newY;
	}

	@Override
	public List<IEntity> executeAction(IEntity player, IEntity npc, IEntityManager myEM) {
		((LocationComponent) player.getComponent(ComponentType.Location)).setXY(teleportXLocation, teleportYLocation);
		player.changed(player);
		return new ArrayList<IEntity>();
	}

	

}
