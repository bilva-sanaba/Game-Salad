package entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;

import components.IComponent;
import engines.IRestrictEntity;
import javafx.beans.InvalidationListener;
/**
 * Class which will represent each GameObject
 * Contains an identifier int and a list of Components
 * @author Bilva
 *
 */
public class Entity implements IEntity,IRestrictEntity {
	private int identifier;
	Collection<IComponent> myComponents;
	
	public Entity(int id){
		identifier = id;
		myComponents= new ArrayList<IComponent>();
	}
	@Override
	public int getID(){
		return identifier;
	}
	@Override
	public void addComponent(IComponent component){
		myComponents.add(component);
	}
	@Override
	public Collection<IComponent> getComponents(){
		return myComponents;
	}
	@Override
	public void addListener(InvalidationListener arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeListener(InvalidationListener arg0) {
		// TODO Auto-generated method stub
		
	}
}
