package engines;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import components.AccelerationComponent;
import components.ComponentType;
import components.IComponent;

public class CollisionPairTest {
	
	CollisionPair cp;
	

	@Test
	public void test() {
		
		ArrayList<IComponent> l = new ArrayList<IComponent>();
		l.add(new AccelerationComponent());
		l.add(new AccelerationComponent());
		CollisionPair cp = new CollisionPair();
		//cp.putPairingFromList(l, 0, 1);
		List<Map<ComponentType, IComponent>> maps = cp.getMaps();
		assertEquals(maps.size(), 2);
		Map<ComponentType, IComponent> abc = Collections.unmodifiableMap(new HashMap<ComponentType, IComponent>());
		
		
	}

}
