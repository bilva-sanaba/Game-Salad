package entity;

import java.util.Collection;

import components.IComponent;
/**
 * Interface for Entity object which represents any object displayed in the game
 * Note: Discuss with GameAuthor team if other methods are needed
 * @author Bilva
 *
 */
public interface IEntity{
	/**
	 * Returns the integer representation of the object
	 * @return int Identifer of Object
	 */
	int getID();
	/**
	 * Adds an IComponents to the Objects Collection of IComponents
	 * @param component to be add to the Collection of IComponents
	 */
	void addComponent(IComponent component);
	/**
	 * Returns all IComponents which the Entity holds
	 * @return Collection<IComponent> which the Entity holds
	 */
	Collection<IComponent> getComponents();

}