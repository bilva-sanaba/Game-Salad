package engines;

import components.ImagePropertiesComponent;
import components.LocationComponent;
import javafx.scene.shape.Rectangle;

public class ObjectCollisionAlgorithm implements ITwoObjectCollide {

	
	public boolean collides(LocationComponent location0, LocationComponent location1, ImagePropertiesComponent image0,
			ImagePropertiesComponent image1) {
		Rectangle obj0 = createRectangle(location0, image0);
		Rectangle obj1 = createRectangle(location1, image1);
		return obj0.getLayoutBounds().intersects(obj1.getLayoutBounds());
	}
	
	private Rectangle createRectangle(LocationComponent xy, ImagePropertiesComponent heightWidth) {
		Rectangle created = new Rectangle();
		created.setX(xy.getX());
		created.setY(xy.getY());
		created.setHeight(heightWidth.getHeight());
		created.setWidth(heightWidth.getWidth());
		return created;
	}

}
