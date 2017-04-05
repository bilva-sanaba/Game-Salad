package components;

/**
 * Component used for determining the location of an object
 * @author Bilva
 *
 */
public class LocationComponent extends XYComponent implements IComponent {
	public LocationComponent(int x,int y){
		super(x,y);
	}
	public LocationComponent(){
		super();
	}
	@Override
	public ComponentType getComponentType() {
		return ComponentType.Location;
	}
}
