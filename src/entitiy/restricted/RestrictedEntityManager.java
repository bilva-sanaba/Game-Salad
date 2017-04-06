package entitiy.restricted;

import java.util.Collection;

import javafx.beans.InvalidationListener;

public class RestrictedEntityManager implements IRestrictedEntityManager {
	private Collection<RestrictedEntity> myRestrictedEntities;
	public RestrictedEntityManager(Collection<RestrictedEntity> entities){
		myRestrictedEntities = entities; 
	}
	@Override
	public void addListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<RestrictedEntity> getEntities() {
		return myRestrictedEntities;
	}

}
