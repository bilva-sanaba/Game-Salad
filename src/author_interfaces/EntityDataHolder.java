package author_interfaces;

import java.util.Collection;
import entity.Entity;

public class EntityDataHolder {
	private Collection<Entity> myEntities;
	
	EntityDataHolder(Collection<Entity> storedEntities){
		myEntities=storedEntities;
	}
	
	public Collection<Entity> getEntities(){
		return myEntities;
	}
	
}
