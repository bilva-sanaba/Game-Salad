package controller;

import java.util.ArrayList;
import java.util.Collection;

import entity.restricted.RestrictedEntity;
import javafx.beans.InvalidationListener;

public class Tracker implements ITracker {
	private String message;
	private Collection<RestrictedEntity> observers;
	
	public Tracker(String message, Collection<RestrictedEntity> entities){
		this.message = message;
		this.observers = entities;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void changeMessage(String newMessage) {
		message = newMessage;
		notifyObservers();
	}
	
	private void notifyObservers() {
		// TODO Auto-generated method stub
		//FACTORY CALL ON observers
	}

}
