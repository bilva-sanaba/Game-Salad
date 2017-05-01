package components.entityComponents;

import components.AComponent;
import components.IComponent;

public class InfiniteOrientationComponent extends AComponent implements IComponent {
	private OrientationComponent myO;
	
	public InfiniteOrientationComponent(OrientationComponent o){
		myO=o;
	}
	
	public InfiniteOrientationComponent(String s) {
		for (OrientationComponent o: OrientationComponent.values()) {
			if (s.equals(o.toString())) {
				myO = o;
			}
		}
	}
	
	public InfiniteOrientationComponent(){
	}
	
	
	public OrientationComponent getOrientation(){
		return myO;
	}
	
	public void setOrientation(OrientationComponent o){
		myO = o;
	}
	
	@Override
	public ComponentType getComponentType() {
		return ComponentType.InfiniteOrientation;
	}

	@Override
	public IComponent newCopy() {
		return new InfiniteOrientationComponent(myO);
	}

}
