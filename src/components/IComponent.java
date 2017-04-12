package components;

import components.entityComponents.ComponentType;

/**
 * Interface which all components (data holding classes inside the entity class)
 * implement
 * 
 * @author Bilva
 *
 */
public interface IComponent {
	public ComponentType getComponentType();
	
	public IComponent newCopy();
}
