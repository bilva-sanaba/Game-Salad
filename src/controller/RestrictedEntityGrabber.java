package controller;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;
import entitiy.restricted.RestrictedEntity;

public class RestrictedEntityGrabber implements Subject {

	private Collection<RestrictedEntity> restrictedEntities;
	
	public RestrictedEntityGrabber(){
		restrictedEntities = new Collection<Observers>();
	}
	
	@Override
	public void register(Observer newObserver) {
		restrictedEntities.add(newObserver);	
	}

	@Override
	public void unregister(Observer o) {
		int observerIndex = restrictedEntities.indexOf(o);
		restrictedEntities.remove(o);	
	}

	@Override
	public void notifyObserver() {
		for(Observer observer : restrictedEntities){
			observer.update(o, arg);
		}
		
	}

}
