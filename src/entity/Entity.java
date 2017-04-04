package entity;
import java.util.ArrayList;
import java.util.Collection;
import components.IComponent;
/**
 * Class which will represent each GameObject
 * @author Bilva
 *
 */
public class Entity implements IEntity {
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
}
