package components.entityComponents;

import components.AComponent;
import components.IComponent;
import entity.IEntity;
/**
 * Component which can be used by various actions to create a new entity. 
 * @author Bilva
 *
 */
public class ObjectCreationComponent extends AComponent implements IComponent{
	private IEntity storedEntity;
	private boolean creating;
	/**
	 * Constructor which stores an entity and sets a boolean to true to indicate that the object 
	 * has been created once
	 * @param e Entity stored in component
	 */
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
	public IEntity checkCreationEffect(){
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
	public boolean checkIfCreation(){
		if (creating==false){
			return false;
		}else{
			return (storedEntity!=null);
		}
	}
	public void setCreating(boolean bool){
		creating=bool;
	}
	@Override
	public ComponentType getComponentType() {
		return ComponentType.ObjectCreation;
	}
	
	@Override
	public IComponent newCopy() {
		ObjectCreationComponent occ= new ObjectCreationComponent(storedEntity.newCopy(0));
		occ.setCreating(creating);
		return occ;
	}
}
