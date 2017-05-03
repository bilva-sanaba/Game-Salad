package components;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import components.entityComponents.BackgroundComponent;
import components.entityComponents.ComponentType;

public abstract class AbstractStringComponent extends AComponent implements IComponent {
	private String myString;

	public AbstractStringComponent(String inputString) {
		myString = inputString;
	}


	public AbstractStringComponent() {
	}


	public String getString() { 
		return myString;
	}

	public void setString(String inputString) {
		myString = inputString;
	}

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
				ctor = c.getConstructor(String.class);
				return (IComponent) ctor.newInstance(myString);
			} catch (NoSuchMethodException| SecurityException | IllegalArgumentException |InstantiationException | IllegalAccessException | InvocationTargetException e) {
				//Add Error handling here.
			}
		}
		return null;
	}
}
