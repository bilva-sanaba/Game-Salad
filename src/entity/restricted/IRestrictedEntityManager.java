package entity.restricted;

import java.util.Collection;

import javafx.beans.Observable;
/**
 * Class which stores information for encapsulated entities sent to front end
 * @author Bilva
 *
 */
public interface IRestrictedEntityManager extends Observable {
	/**
	 * 
	 * @return Collection of entities with restricted data
	 */
	public Collection<RestrictedEntity> getEntities();
	
}
