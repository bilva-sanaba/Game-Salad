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
	/**
	 * Returns a type from an enum ComponentType. 
	 * This allows for searching for a certain component by type
	 * @return
	 */
	public ComponentType getComponentType();
	
	public IComponent newCopy();
}
