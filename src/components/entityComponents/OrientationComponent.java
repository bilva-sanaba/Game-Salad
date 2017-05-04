package components.entityComponents;

import components.AComponent;
import components.IComponent;

public class OrientationComponent extends AComponent implements IComponent{

	private int myOrientation = 0;
	
	public static final int LEFT = 180;
	public static final int RIGHT = 0;
	
	@Override
	public ComponentType getComponentType() {
		return ComponentType.Orientation;
	}
	
	public int getOrientation(){
		return myOrientation;
	}
	
	public void setOrientation(int orientation){
		myOrientation = orientation;
	}

	@Override
	public IComponent newCopy() {
		OrientationComponent myOrientationComponent = new OrientationComponent();
		myOrientationComponent.setOrientation(this.getOrientation());
		return myOrientationComponent;
	}

}
