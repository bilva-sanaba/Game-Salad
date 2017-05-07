// This entire file is part of my masterpiece.
// Bilva Sanaba
// This is a concrete version of an icomponent which extends the AbstractBooleanComponent showing how little code 
// the class needs now that a majority of it could be extracted to the super class. This is also the component that is checked by
// the ComplexInfiniteLoopAlgorithm to determine if an entity should be looped connecting all pieces of my masterpiece
package components.entityComponents;

import components.AbstractBooleanComponent;
import components.IComponent;
/**
 * Component which can be used by the infinite engine to 
 * run algorithms differently
 * @author Bilva
 *
 */
public class StationaryComponent extends AbstractBooleanComponent implements IComponent {
	private static final boolean DEFAULT_SET = false;
	public StationaryComponent(boolean s) {
		super();
	}
	
	public StationaryComponent(){
		super();
		setBoolean(DEFAULT_SET);
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Stationary;
	}
}