package components.entityComponents;

import components.AComponent;
import components.IComponent;

public class InfiniteSpacingComponent extends AComponent implements IComponent {
	private double spacing;

	public InfiniteSpacingComponent(double s) {
		spacing = s;
	}
	
	public InfiniteSpacingComponent(){
		super();
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.InfiniteSpacing;
	}

	public double getInfiniteSpacingComponent() {
		return spacing;
	}

	public void setInfiniteSpacingComponent(double s) {
		spacing = s;
	}

	public IComponent newCopy() {
		return new InfiniteSpacingComponent(getInfiniteSpacingComponent());
	}

}
