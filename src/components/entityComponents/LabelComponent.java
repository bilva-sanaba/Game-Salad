package components.entityComponents;

import components.AbstractOneParameterComponent;
import components.AbstractStringComponent;
import components.IComponent;

/**
 * Component which is useful for grouping together objects with similar
 * functionality
 * 
 * @author Bilva
 *
 */
public class LabelComponent extends AbstractStringComponent implements IComponent {

	public LabelComponent(String l) {
		super(l);
	}

	public LabelComponent() {

	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Label;
	}
}
