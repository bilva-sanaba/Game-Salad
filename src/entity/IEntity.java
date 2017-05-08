package entity;

import java.util.Collection;

import components.IComponent;
import components.entityComponents.ComponentType;
import javafx.beans.Observable;

/**
 * Interface for Entity object which represents any object displayed in the game
 * Note: Discuss with GameAuthor team if other methods are needed
 * 
 * @author Bilva
 *
 */
public interface IEntity {
	/**
	 * Returns the integer representation of the object
	 * 
	 * @return int Identifer of Object
	 */
	public int getID();

	/**
	 * sets a new integer to be the identifier of the object
	 */
	public void setID(int i);

	/**
	 * Adds an IComponents to the Objects Collection of IComponents
	 * 
	 * @param component
	 *            to be add to the Collection of IComponents
	 */
	public void addComponent(IComponent component);

	/**
	 * Returns all IComponents which the Entity holds
	 * 
	 * @return Collection<IComponent> which the Entity holds
	 */
	public Collection<IComponent> getComponents();
	/**
	 * Checks if the specified icomponent exist
	 * @param ct the component type
	 * @return boolean if Entity has component
	 */
	public boolean hasComponent(ComponentType ct);
	/**
	 * Returns the specified icomponent and null if it does not exist
	 * @param ct the component type
	 * @return the icomponent or null if it does not exist
	 */

	public IComponent getComponent(ComponentType ct);
	@Deprecated 
	public IComponent getComponent(IComponent ic);
	/**
	 * Returns the exact same version of the entity without a pointer
	 * @return
	 */
	public IEntity clone();
	/**
	 * For notifying an observer of changes to the entity which must display 
	 * @param o
	 */
	public void changed(Object o);
	/**
	 * Returns the same entity without a pointer and a new ID
	 * @param size
	 * @return
	 */
	public IEntity newCopy(int size);
}