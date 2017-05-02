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
		try {
			ctor = c.getConstructor(String.class);
			return (IComponent) ctor.newInstance(myString);
		} catch (NoSuchMethodException| SecurityException | IllegalArgumentException |InstantiationException | IllegalAccessException | InvocationTargetException e) {
			//Add Error handling here.
			return null;
		}
	}
}
