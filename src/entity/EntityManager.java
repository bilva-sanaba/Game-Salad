package entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import components.IComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.SpriteComponent;
import components.entityComponents.XYComponent;
import entity.restricted.IRestrictedEntity;
import entity.restricted.IRestrictedEntityManager;
import gameView.Coordinate;

public class EntityManager extends Observable implements IEntityManager, IRestrictedEntityManager  {
	private Collection<IEntity> myEntities;

	public EntityManager(Collection<Entity> entities){
		myEntities = new ArrayList<IEntity>();
		for (Entity e: entities) {
			if (e.getComponent(ComponentType.Location) != null) {
				myEntities.add(e);
			}
		}
	}

	@Override
	public Map<Integer, IComponent> getCertainComponents(
			ComponentType certainComponent) {
		Map<Integer, IComponent> certainComponents = new HashMap<Integer, IComponent>();
		for (IEntity e : myEntities) {

			certainComponents.put(e.getID(), e.getComponent(certainComponent));
		}
		return certainComponents;
	}
	public Collection<IEntity> copy(){
		Collection<IEntity> copy = new ArrayList<IEntity>();
		for (IEntity e: myEntities){
			IEntity entCopy = e.clone();
			copy.add(e);
		}
		return copy;
	}
	
	public IEntity getEntityByID(int ID){
		for(IEntity currentEntity: myEntities){
			if(currentEntity.getID() == ID){
				return currentEntity;
			}
		}
		return null;
	}

	@Override
	public Collection<IRestrictedEntity> getRestrictedEntities() {
		Collection<IRestrictedEntity> restricted = new ArrayList<IRestrictedEntity>();
		for (IEntity e : myEntities){
			restricted.add((IRestrictedEntity) e);
		}
		return restricted;
	}

	public Collection<IEntity> getEntities() {
		// TODO Auto-generated method stub
		return myEntities;
	}
}
