package gameEngine_interface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import components.IComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import entity.IEntity;
import entity.IEntityManager;

public class EntityLoader {
	private IEntityManager mainEntityManager;
	
	public EntityLoader(IEntityManager mainManager){
		mainEntityManager = mainManager;
	}
	public void loadNew(IEntityManager newManager) {
		Map<Integer, IEntity> replaced = new HashMap<Integer, IEntity>();
		for (IEntity e : mainEntityManager.getEntities()){
			e.changed(null);
			if (e.getComponent(ComponentType.KeyInput)!=null){
				replaced.put(e.getID(), e);
			}
		}
		mainEntityManager.getEntities().clear();
		for (IEntity e : newManager.getEntities()){
			if (replaced.keySet().contains(e.getID())){
				IEntity oldE = replaced.get(e.getID());
				recreateMainCharacter(oldE,e);
				e=oldE;
			}
				mainEntityManager.changed(e);
				mainEntityManager.getEntities().add(e);	
		}
	}

	private void recreateMainCharacter(IEntity oldE, IEntity e){
		for (IComponent oldC : oldE.getComponents().toArray(new IComponent[oldE.getComponents().size()])){
			if (!oldC.equals(oldE.getComponent(ComponentType.Location))){
				oldE.getComponents().remove(oldC);
			}
		}
		for (IComponent c : e.getComponents()){
			if (c.equals(e.getComponent(ComponentType.Location))){
				LocationComponent lc = (LocationComponent) oldE.getComponent(ComponentType.Location);
				lc.setX(((LocationComponent) c).getX());
				lc.setY(((LocationComponent) c).getY());
				
			}else{
			oldE.addComponent(c);
			}
		}
	}
}
