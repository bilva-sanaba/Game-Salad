package entity.restricted;

import java.util.Collection;
import java.util.Observer;

import javafx.beans.Observable;

/**
 * Class which stores information for encapsulated entities sent to front end
 * 
 * @author Bilva
 *
 */
public interface IRestrictedEntityManager  {
	/**
	 * 
	 * @return Collection of entities with restricted data
	 */
	
	public Collection<IRestrictedEntity> getRestrictedEntities();

	public void addObserver(Observer obs);
	public void changed(Object o);
}
