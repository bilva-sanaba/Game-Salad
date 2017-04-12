package components.entityComponents;

import components.IComponent;

/**
 * Component which is useful for grouping together objects with similar
 * functionality
 * 
 * @author Bilva
 *
 */
public class LabelComponent implements IComponent {

	private String label;

	public LabelComponent(String l) {
		label = l;
	}

	public LabelComponent() {

	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Label;
	}

	public void setLabel(String newLabel) {
		label = newLabel;
	}

	public String getLabel() {
		return label;
	}
	
	public IComponent newCopy() {
		return new LabelComponent(getLabel());
	}
}
