package engines;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import components.ComponentType;
import components.IComponent;
import components.ImagePropertiesComponent;
import components.LocationComponent;
import javafx.scene.shape.Rectangle;

public class ObjectCollisionAlgorithm implements ITwoObjectCollide {

	
	public String collides(Map<ComponentType, IComponent> obj0, Map<ComponentType, IComponent> obj1) {
		ImagePropertiesComponent img0 = (ImagePropertiesComponent) obj0.get(ComponentType.ImageProperties);
	}
	
	/*private Rectangle createRectangle(LocationComponent xy, ImagePropertiesComponent heightWidth) {
		Rectangle created = new Rectangle();
		created.setX(xy.getX());
		created.setY(xy.getY());
		created.setHeight(heightWidth.getHeight());
		created.setWidth(heightWidth.getWidth());
		return created;
	}*/

	@Override
	public List<ComponentType> needsComponents() {
		List<ComponentType> neededComp = new ArrayList<ComponentType>();
		neededComp.add(ComponentType.ImageProperties);
		neededComp.add(ComponentType.Velocity);
		neededComp.add(ComponentType.Location);
		return neededComp;


	}

}
