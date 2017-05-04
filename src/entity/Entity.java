package entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import components.IComponent;
import components.entityComponents.CollidableComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.SpriteComponent;
import components.entityComponents.TypeComponent;
import entity.restricted.IRestrictedEntity;
import javafx.geometry.Dimension2D;
import javafx.scene.image.ImageView;

/**
 * Class which will represent each GameObject Contains an identifier int and a
 * list of Components
 * 
 * @author Bilva
 *
 */
public class Entity extends Observable implements IEntity, IRestrictedEntity {
	private int identifier;
	private Map<IComponent, ComponentType> myComponentMap;
	

	public Entity(int id) {
		identifier = id;
		myComponentMap = new HashMap<IComponent, ComponentType>();
	}

	public Entity clone() {
		Entity temp = new Entity(identifier);
		for(IComponent a : myComponentMap.keySet()){
			temp.addComponent(a.newCopy());
		}
		return temp;
	}
	public Entity newCopy(int size){
		Entity temp = new Entity(identifier+size);
		for (IComponent a : myComponentMap.keySet()){
			temp.addComponent(a.newCopy());
		}
		return temp;
	}

	@Override
	public int getID() {
		return identifier;
	}
	
	public EntityType getType() {
		TypeComponent type = (TypeComponent) this.getComponent(ComponentType.Type);
		if (type != null) {
			return type.getType();
		}
		return EntityType.None;
	}
	
	@Override
	public void setID(int i) {
		identifier = i;
	}

	@Override
	public void addComponent(IComponent component) {
		if (myComponentMap.containsKey(component)){
				myComponentMap.remove(component);
		}
		myComponentMap.put(component, component.getComponentType());
	}

	@Override
	public Collection<IComponent> getComponents() {
		return myComponentMap.keySet();
	}

	public IComponent getComponent(ComponentType ct) {
		for (IComponent myComponent : myComponentMap.keySet()) {
			if (myComponent.getComponentType() == ct) {
				return myComponent;
			}
		}
		return null;
	}
	
	public boolean containsComponent(ComponentType type){
		return myComponentMap.values().contains(type);
	}
	
	public IComponent getComponent(IComponent ic){
		IComponent it = ic; 
		for (IComponent myComponent : myComponentMap.keySet()) {
			if (myComponent.getClass().equals(it.getClass()) ){
				return myComponent;
			}
		}
		return null;
	}
	
	@Override
	public String toString(){
		if(((LabelComponent)this.getComponent(ComponentType.Label)).getString() != null){
			return ((LabelComponent)this.getComponent(ComponentType.Label)).getString();
		}
		return "null label component";
	}

	@Override
	public Dimension2D getRestrictedLocation() {
		LocationComponent lc = (LocationComponent) getComponent(ComponentType.Location);
		return new Dimension2D(lc.getX(), lc.getY());

	}

	@Override
	public String getRestrictedImagePath() {
		SpriteComponent sc = (SpriteComponent) getComponent(ComponentType.Sprite);
		return sc.getString();
	}

	@Override
	public Dimension2D getRestrictedIPComponent() {
		ImagePropertiesComponent ip = (ImagePropertiesComponent) getComponent(ComponentType.ImageProperties);
		Dimension2D location = new Dimension2D(ip.getWidth(), ip.getHeight());
		return location;
	}
	
	public ImageView getImageView(){
		SpriteComponent entitySprite = (SpriteComponent) this.getComponent(ComponentType.Sprite);
		ImageView spriteImage = new ImageView(entitySprite.getSprite());
		if (this.getComponent(ComponentType.ImageProperties) != null) {
			ImagePropertiesComponent imageProp = (ImagePropertiesComponent) this.getComponent(ComponentType.ImageProperties);
			spriteImage.setFitHeight(imageProp.getHeight());
			spriteImage.setFitWidth(imageProp.getWidth());
		} else {
			spriteImage.setFitHeight(40);
			spriteImage.setFitWidth(40);
		}
		return spriteImage;
		
	}

	@Override
	public void changed(Object o) {
		setChanged();
		notifyObservers(o);
	}

	@Override
	public boolean hasComponent(ComponentType ct) {
		return this.getComponent(ct)!=null;
	}
	
	
}