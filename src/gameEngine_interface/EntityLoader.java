package gameEngine_interface;

import entity.IEntity;
import entity.IEntityManager;

public class EntityLoader {
	private IEntityManager mainEntityManager;
	
	public EntityLoader(IEntityManager mainManager){
		mainEntityManager = mainManager;
	}
	public void loadNew(IEntityManager newManager) {
		for (IEntity e : mainEntityManager.getEntities()){
			e.changed(null);
		}
		mainEntityManager.getEntities().clear();
		mainEntityManager.getEntities().addAll(newManager.getEntities());
		for (IEntity e : newManager.getEntities()){
			mainEntityManager.changed(e);
		}
		
	}
	
}
