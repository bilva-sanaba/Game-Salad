package gameView.managers;

import java.util.Collection;
import java.util.Iterator;

import components.IComponent;

public class Manager {
	
	private Collection<IComponent> myCollection;
	
	public Manager(Collection<IComponent> collection) {
		myCollection = collection;
	}
	
	public Iterator<IComponent> getCollection() {
		return myCollection.iterator();
	}
	
	public void update(IComponent update) {
		
	}
	
}
