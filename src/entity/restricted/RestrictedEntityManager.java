package entity.restricted;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import javafx.beans.InvalidationListener;

public class RestrictedEntityManager extends Observable implements
		IRestrictedEntityManager {
	private Collection<RestrictedEntity> myRestrictedEntities;

	public RestrictedEntityManager(Collection<RestrictedEntity> entities) {
		myRestrictedEntities = entities;
	}

	@Override
	public Collection<RestrictedEntity> getEntities() {
		return myRestrictedEntities;
	}
	
	public Collection<IRestrictedEntity> getIEntities() {
		Collection<IRestrictedEntity> newCollection = new ArrayList<IRestrictedEntity>();
		myRestrictedEntities.stream()
			.forEach(c -> newCollection.add((IRestrictedEntity) c));
		return newCollection;
	}

	@Override
	public void addObserver(Observer obs) {
		this.addObserver(obs);
	}

}
