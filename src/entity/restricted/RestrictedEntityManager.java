package entity.restricted;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import javafx.beans.InvalidationListener;

public class RestrictedEntityManager extends Observable implements IRestrictedEntityManager {
	private Collection<IRestrictedEntity> myRestrictedEntities;
	public RestrictedEntityManager(Collection<IRestrictedEntity> entities){
		myRestrictedEntities = entities; 
	}
	
	@Override
	public Collection<IRestrictedEntity> getEntities() {
		return myRestrictedEntities;
	}
	@Override
	public void addObserver(Observer obs) {
		this.addObserver(obs);	
	}

}
