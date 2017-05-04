package components.entityComponents;

import java.util.HashMap;
import java.util.Map;

import components.AComponent;
import components.IComponent;

public class CollisionComponentsHandler extends AComponent implements IComponent {
	private Map<String, SideCollisionComponent> sideCollisionMap;
	
	
	public static final String TOP = "Top";
	public static final String LEFT = "Left";
	public static final String BOTTOM = "Bottom";
	public static final String RIGHT = "Right";
	
	public CollisionComponentsHandler(Map <String, SideCollisionComponent> map){
		sideCollisionMap = map;
		
	}
	public CollisionComponentsHandler() {
		sideCollisionMap = new HashMap<String, SideCollisionComponent>();
	}
	
	public void addCollisionComponent(SideCollisionComponent collisionComponent) {
		if (collisionComponent!=null) {
			sideCollisionMap.put(collisionComponent.whichSide().toString(), collisionComponent);
		}
	}
	
	/**
	 * Returns the collision component for a given side of an entity. An input of "Left" would return the CollisionComponent for the left side of the entity.
	 * RETURNS NULL IF side INPUT IS NULL OR IS NOT FOUND.
	 * @param side String representing which side you want the CollisionComponent from.
	 * @return SideCollisionComponent
	 
	 */
	public SideCollisionComponent getCollisionComponent(String side) {
		if (side != null && sideCollisionMap.containsKey(side)) {
			return sideCollisionMap.get(side);
		}
		return null;
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.CollisionHandler;
	}

	@Override
	public IComponent newCopy() {
		Map<String, SideCollisionComponent> copyMap = new HashMap<String, SideCollisionComponent>();
		for (String s: sideCollisionMap.keySet()) {
			copyMap.put(s, (SideCollisionComponent) sideCollisionMap.get(s).newCopy());
		}
		return new CollisionComponentsHandler(copyMap);
	}
}
