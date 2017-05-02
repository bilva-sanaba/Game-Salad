package components;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import components.entityComponents.ComponentType;

public abstract class AbstractOneParameterComponent<E>  implements IComponent{
	private E myString;

	public AbstractOneParameterComponent(E obj){
		myString = obj;
	}
	public AbstractOneParameterComponent(){}
	
	public E getObject(){
		return myString;
	}

	public void setObject(E obj){
		myString = obj;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public IComponent newCopy() {
		Class c = this.getClass();
		Constructor ctor;
		try {
			ctor = c.getConstructor(getObject().getClass());
			return (IComponent) ctor.newInstance(myString);
		} catch (NoSuchMethodException| SecurityException | IllegalArgumentException |InstantiationException | IllegalAccessException | InvocationTargetException e) {
			// Add error checking
		}
		return null;
	}
}
