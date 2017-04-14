package entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import components.IComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.SpriteComponent;
import entity.restricted.IRestrictedEntity;
import gameView.Coordinate;
import javafx.beans.InvalidationListener;
import javafx.geometry.Dimension2D;

/**
 * Class which will represent each GameObject Contains an identifier int and a
 * list of Components
 * 
 * @author Bilva
 *
 */
public class Entity extends Observable implements IEntity, IRestrictedEntity {
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

	public IComponent getComponent(ComponentType ct) {
		for (IComponent myComponent : myComponents) {
			if (myComponent.getComponentType() == ct) {
				return myComponent;
			}
		}
		
		return null;
	}
	
	@Override
	public String toString(){
		if(((LabelComponent)this.getComponent(ComponentType.Label)).getLabel() != null){
			return ((LabelComponent)this.getComponent(ComponentType.Label)).getLabel();
		}
		return "null label component";
	}

	@Override
	public Dimension2D getLocation() {
		LocationComponent lc = (LocationComponent) getComponent(ComponentType.Location);
		return new Dimension2D(lc.getX(), lc.getY());
	}

	@Override
	public String getRestrictedImagePath() {
		SpriteComponent sc = (SpriteComponent) getComponent(ComponentType.Location);
		return sc.getClassPath();
	}

	@Override
	public Dimension2D getRestrictedIPComponent() {
		ImagePropertiesComponent ic = (ImagePropertiesComponent) getComponent(ComponentType.Location);
		return new Dimension2D(ic.getWidth(), ic.getHeight());
	}

}