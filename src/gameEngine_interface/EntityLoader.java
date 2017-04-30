package gameEngine_interface;

import java.util.ArrayList;
import java.util.List;

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
		List<IEntity> nonReplaced = new ArrayList<IEntity>();
		List<Integer> newNonReplaced = new ArrayList<Integer>();
		for (IEntity e : mainEntityManager.getEntities()){
			e.changed(null);
			if (e.getComponent(ComponentType.KeyInput)!=null){
				nonReplaced.add(e);
				newNonReplaced.add(e.getID());
			}
		}
		mainEntityManager.getEntities().clear();
		for (IEntity e : newManager.getEntities()){
			if (newNonReplaced.contains(e.getID())){
				IEntity oldE = nonReplaced.get(e.getID());
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
