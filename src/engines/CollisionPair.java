package engines;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import components.IComponent;
import components.entityComponents.ComponentType;

public class CollisionPair {
	
	public static final int OBJECT0_INDEX = 0;
	public static final int OBJECT1_INDEX = 1;


	private Map<ComponentType, IComponent> object0;
	private Map<ComponentType, IComponent> object1;
	
	public CollisionPair() {
		object0 = new HashMap<ComponentType, IComponent>();
		object1 = new HashMap<ComponentType, IComponent>();
	}
	
	public void putPairingFromList(Map<Integer, IComponent> compList, int index0, int index1) {
		try {
			ComponentType type = compList.get(compList.size()-1).getComponentType();
			IComponent object0Comp = compList.get(index0);
			IComponent object1Comp = compList.get(index1);
			object0.put(type, object0Comp);
			object1.put(type, object1Comp);
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Exception handling needs to be replaced in CollisionPair.java in putPairingFromList()");
		}
		
	}
	
	public List<Map<ComponentType, IComponent>> getMaps() {
		Map<ComponentType, IComponent> obj0UnMod = Collections.unmodifiableMap(object0);
		Map<ComponentType, IComponent> obj1UnMod = Collections.unmodifiableMap(object1);
		List<Map<ComponentType, IComponent>> mapList = new ArrayList<Map<ComponentType, IComponent>>();
		mapList.add(obj0UnMod);
		mapList.add(obj1UnMod);
		return mapList;

	}
	
}
