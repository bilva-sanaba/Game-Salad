package gameEngine_interface;

import components.IComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import entity.Entity;
import entity.IEntity;
import entity.IEntityManager;
/**
 * Class which allows for the changing of an entity Manager into another entity manager
 * @author Bilva
 *
 */
public class EntityLoader implements IEntityLoader {
	private IEntityManager mainEntityManager;
	
	public EntityLoader(IEntityManager mainManager){
		mainEntityManager = mainManager;
	}

	@Override
	public void loadNew(IEntityManager newManager) {
		IEntity originalMain = new Entity(0);
		for (IEntity e : mainEntityManager.getEntities()){
			e.changed(null);
			if (e.getComponent(ComponentType.KeyInput)!=null){
				originalMain=e;
			}
		}
		mainEntityManager.getEntities().clear();
		for (IEntity e : newManager.getEntities()){
			if (e.hasComponent(ComponentType.KeyInput)){
				IEntity oldE = originalMain;
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
