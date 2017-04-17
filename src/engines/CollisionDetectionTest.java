package engines;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import components.IComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.movementcomponents.LocationComponent;

public class CollisionDetectionTest {
	
	private ITwoObjectCollide collisionAlgo;
	private Map<ComponentType, IComponent> obj0;
	private Map<ComponentType, IComponent> obj1;

	
	public void initialize(double x0, double x1, double y0, double y1, double width0, double width1, double height0, double height1) {
		collisionAlgo = new ObjectCollisionAlgorithm();
		LocationComponent lc0 = new LocationComponent();
		LocationComponent lc1 = new LocationComponent();
		ImagePropertiesComponent img0 = new ImagePropertiesComponent();
		ImagePropertiesComponent img1 = new ImagePropertiesComponent();
		lc0.setX(x0);
		lc0.setY(y0);
		lc1.setX(x1);
		lc1.setY(y1);
		img0.setHeight(width0);
		img1.setHeight(height0);
		img0.setWidth(width1);
		img1.setWidth(height1);
		obj0 = new HashMap<ComponentType, IComponent>();
		obj1 = new HashMap<ComponentType, IComponent>();
		obj0.put(ComponentType.Location, lc0);
		obj1.put(ComponentType.Location, lc1);
		obj0.put(ComponentType.ImageProperties, img0);
		obj1.put(ComponentType.ImageProperties, img1);
	}
	
	@Test
	public void testTop() {
		//BOTTOM TEST
		initialize(5, 5, 8, 10, 2, 5, 3, 10);
		assertEquals("Top", collisionAlgo.collides(obj1, obj0));


		
	}
	
	@Test
	public void testBottom() {
		initialize(5, 5, 10, 8, 5, 5, 3, 10);
		assertEquals("Bottom", collisionAlgo.collides(obj1, obj0));
		
	}

}
