package components;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import components.entityComponents.ComponentType;

public abstract class AbstractOneParameterComponent<E> extends AComponent  implements IComponent{
	private E myString;
	public AbstractOneParameterComponent(E obj){
		myString = obj;
	}
	public AbstractOneParameterComponent(){}
	
	public E getString(){
		return myString;
	}	
	
	public void setString(E obj){
		myString = obj;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public IComponent newCopy() {
		Class c = this.getClass();
		Constructor ctor;
		if (myString==null){
			try {
				ctor = c.getConstructor();
				return (IComponent) ctor.newInstance();
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				//add Error Handline here;
			}


		}else {
			try {
				ctor = c.getConstructor(myString.getClass());
				return (IComponent) ctor.newInstance(myString);
			} catch (NoSuchMethodException| SecurityException | IllegalArgumentException |InstantiationException | IllegalAccessException | InvocationTargetException e) {
				//Add Error handling here.
			}
		}
		return null;
	}
	
}
