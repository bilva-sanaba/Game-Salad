package author_interfaces;

import java.util.Collection;
import entity.Entity;
/**
 * Basic interface for storing a collection of entities needed for a level
 * @author Bilva
 *
 */
public interface IEntityDataHolder {
	public Collection<Entity> getEntities();
}
