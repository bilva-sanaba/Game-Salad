package entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;

import components.ComponentType;
import components.IComponent;
import components.LabelComponent;
import engines.IRestrictEntity;
import entity.restricted.IRestrictedEntity;
import gameView.Coordinate;
import javafx.beans.InvalidationListener;

/**
 * Class which will represent each GameObject Contains an identifier int and a
 * list of Components
 * 
 * @author Bilva
 *
 */
public class Entity implements IEntity, IRestrictEntity {
	private int identifier;
	Collection<IComponent> myComponents;

	public Entity(int id) {
		identifier = id;
		myComponents = new ArrayList<IComponent>();
	}

	public Entity clone() {
		Entity temp = new Entity(identifier);
		for(IComponent a : myComponents){
			temp.addComponent(a.newCopy());
		}
		return temp;
	}

	@Override
	public int getID() {
		return identifier;
	}
	
	@Override
	public void setID(int i) {
		identifier = i;
	}

	@Override
	public void addComponent(IComponent component) {
		myComponents.add(component);
	}

	@Override
	public Collection<IComponent> getComponents() {
		return myComponents;
	}

	/*
	 * @Override public void addListener(InvalidationListener arg0) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void removeListener(InvalidationListener arg0) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 */

	public IComponent getComponent(ComponentType ct) {
		for (IComponent myComponent : myComponents) {
			if (myComponent.getComponentType() == ct) {
				return myComponent;
			}
		}
		
		return null;
	}

	@Override
	public void addListener(InvalidationListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeListener(InvalidationListener arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString(){
		if(((LabelComponent)this.getComponent(ComponentType.Label)).getLabel() != null){
			return ((LabelComponent)this.getComponent(ComponentType.Label)).getLabel();
		}
		return "null label component";
	}
}