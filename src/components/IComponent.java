// This entire file is part of my masterpiece.
// Bilva Sanaba
// This interface highlights the main principle of design in the overall game engine, an entity-component based design
// which relies on composition rather than inheritance to make the entities of the game. 
package components;

import components.entityComponents.ComponentType;
import exceptions.CopyException;

/**
 * Interface which all components (data holding classes inside the entity class)
 * implement
 * @author Bilva
 */
public interface IComponent {
	/**
	 * Returns a type from an enum ComponentType. 
	 * This allows for searching for a certain component by type
	 * @return
	 */
	public ComponentType getComponentType();
	/**
	 * Creates new deep copy of IComponent
	 * @return
	 * @throws CopyException
	 */
	public IComponent newCopy() throws CopyException;
}