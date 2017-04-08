package engines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import components.ComponentType;
import components.IComponent;
import components.ImagePropertiesComponent;
import components.LocationComponent;
import entity.IEntityManager;
/**
 * This engine handles all collisions
 * When update is called it should use all needed CollisionSubEngines
 * (This provides an important area for design choices as the neededComponents method would need to be changed if
 * more collision sub engines are added which use different components)
 * @author Bilva
 *
 */
public class CollisionEngine extends AbstractEngine implements ICollision{
	
	private List<ISubEngine> subEngines;
	private IEntityManager entManager;

	public CollisionEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
		subEngines = new ArrayList<ISubEngine>();
		entManager = myEntityManager;
	}
	
	public void addEngine(ISubEngine newSubEngine) {
		newSubEngine.addEntityManager(entManager);
		subEngines.add(newSubEngine);
		
	}
	
	public void checkCollisionsOccurred() {
		List<IComponent> locationComponents = entManager.getCertainComponents(ComponentType.Location);
		List<IComponent> imageComponents = entManager.getCertainComponents(ComponentType.ImageProperties);
		int componentIndex0 = -1;
		for (IComponent location : locationComponents) {
			componentIndex0++;
			int componentIndex1 = -1;
			for (IComponent otherLocation : locationComponents) {
				componentIndex1++;
				if (componentIndex1 != componentIndex0) {
					checkIndividualCollision(location, otherLocation, imageComponents.get(componentIndex0), imageComponents.get(componentIndex1), componentIndex0, componentIndex1);
				}
			}
		}
	}
	
	
	private void checkIndividualCollision(IComponent location0, IComponent location1, IComponent imageProp0, IComponent imageProp1, int index0, int index1) {
		ImagePropertiesComponent img0 = (ImagePropertiesComponent) imageProp0;
		ImagePropertiesComponent img1 = (ImagePropertiesComponent) imageProp1;
		LocationComponent loc0 = (LocationComponent) location0;
		LocationComponent loc1 = (LocationComponent) location1;
		boolean collisionOccurs = false;
		//TODO: check if collision occurs
		
		if (collisionOccurs) {
			for(ISubEngine subEngine: subEngines) {
				List<ComponentType> subEngineNeededComponents = subEngine.getNecessaryComponents();
				Map<ComponentType, IComponent> object0Components = new HashMap<ComponentType, IComponent>();
				Map<ComponentType, IComponent> object1Components = new HashMap<ComponentType, IComponent>();
				for(ComponentType comp : subEngineNeededComponents) {
					
				}
			}
		}
	}

	@Override
	public List<ComponentType> neededComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
