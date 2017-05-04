package components.entityComponents;

import components.AComponent;
import components.AbstractOneParameterComponent;
import components.IComponent;

public class InfiniteSpacingComponent extends AbstractOneParameterComponent<Double> implements IComponent {
	

	public InfiniteSpacingComponent(double s) {
		super(s);
	}
	
	public InfiniteSpacingComponent(){
		super();
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.InfiniteSpacing;
	}
}
