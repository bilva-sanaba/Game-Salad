package controller;

import java.util.ArrayList;

import javafx.beans.InvalidationListener;

public class Tracker implements ITracker {
	private String message;
	private ArrayList<InvalidationListener> observers;
	
	public Tracker(String message, ArrayList<InvalidationListener> observers){
		this.message = message;
		this.observers = observers;
	}
	@Override
	public void addListener(InvalidationListener listener) {
		observers.add(listener);
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		observers.remove(listener);
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
