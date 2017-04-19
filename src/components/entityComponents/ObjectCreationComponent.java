package components.entityComponents;

import components.IComponent;
import entity.Entity;
import entity.IEntity;

public class ObjectCreationComponent implements IComponent{
	private IEntity storedEntity;
	private boolean creating;
	public ObjectCreationComponent(IEntity e){
		storedEntity=e;
		creating = true;
	}
	public void setEntity(IEntity e){
		storedEntity=e;
	}
	public IEntity getStoredEntity(){
		return storedEntity;
	}
	public IEntity getCreationEffect(){
		if (creating){
			creating = !creating;
			return storedEntity;
		}
		else{
			return null;
		}
	}
	@Override
	public ComponentType getComponentType() {
		// TODO Auto-generated method stub
		return ComponentType.ObjectCreation;
	}
	@Override
	public IComponent newCopy() {
		// TODO Auto-generated method stub
		return null;
	}
}
