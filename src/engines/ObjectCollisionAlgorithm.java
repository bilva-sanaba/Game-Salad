package engines;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import components.entityComponents.ComponentType;
import components.IComponent;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.VelocityComponent;
import javafx.scene.shape.Rectangle;
public class ObjectCollisionAlgorithm implements ITwoObjectCollide {
	
	public ObjectCollisionAlgorithm() {
		
	}
	
	public String collides(Map<ComponentType, IComponent> obj0, Map<ComponentType, IComponent> obj1) {
		ImagePropertiesComponent img0 = (ImagePropertiesComponent) obj0.get(ComponentType.ImageProperties);
		ImagePropertiesComponent img1 = (ImagePropertiesComponent) obj1.get(ComponentType.ImageProperties);
		LocationComponent loc0 = (LocationComponent) obj0.get(ComponentType.Location);
		LocationComponent loc1 = (LocationComponent) obj1.get(ComponentType.Location);
		Rectangle r0 = createRectangle(loc0, img0);
		Rectangle r1 = createRectangle(loc1, img1);
		if (checkRectangleCollide(r0, r1)) {
			return whichSide(r0, r1);
		}
		return ITwoObjectCollide.NONE;
		
	}
	
	private String whichSide(Rectangle r0, Rectangle r1) {
		float w = (float) (0.5 * (r0.getWidth() + r1.getWidth()));
		float h = (float) (0.5 * (r0.getHeight() + r1.getHeight()));
		float dx = (float) ((r0.getX()+ (0.5 * r0.getWidth())) - (r1.getX() + (0.5 *r1.getWidth())));
		float dy = (float) ((float) ((r0.getY()+ (0.5 * r0.getHeight()))) - (r1.getY() + (0.5 *r1.getHeight())));
		
		float wy = w*dy;
		float hx = h*dx;
		if (wy > hx) {
			if (wy > -hx) {
				return ITwoObjectCollide.TOP;
			} else {
				return ITwoObjectCollide.LEFT;
			}
		}
		if (wy >-hx) {
			return ITwoObjectCollide.RIGHT;
		}
		return ITwoObjectCollide.BOTTOM;
		
	}
	
	private Rectangle createRectangle(LocationComponent loc0, ImagePropertiesComponent img0) {
		Rectangle r = new Rectangle();
		r.setX(loc0.getX());
		r.setY(loc0.getY());
		r.setWidth(img0.getWidth());
		r.setHeight(img0.getHeight());
		return r;
	}
	
	
	private boolean checkRectangleCollide(Rectangle r0, Rectangle r1) {
		return r0.getBoundsInLocal().intersects(r1.getBoundsInLocal());
	}
	
	@Override
	public List<ComponentType> needsComponents() {
		List<ComponentType> neededComp = new ArrayList<ComponentType>();
		neededComp.add(ComponentType.ImageProperties);
		neededComp.add(ComponentType.Location);
		return neededComp;
	}
}
