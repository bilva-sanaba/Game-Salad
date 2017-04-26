package components.entityComponents;

import components.AComponent;
import components.IComponent;

public class InfiniteOrientationComponent extends AComponent implements IComponent {
	private Orientation myO;
	
	public InfiniteOrientationComponent(Orientation o){
		myO=o;
	}
	
	public InfiniteOrientationComponent(String s) {
		for (Orientation o: Orientation.values()) {
			if (s.equals(o.toString())) {
				myO = o;
			}
		}
	}
	
	public InfiniteOrientationComponent(){
	}
	
	
	public Orientation getOrientation(){
		return myO;
	}
	
	public void setOrientation(Orientation o){
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
