// This entire file is part of my masterpiece.
// Bilva Sanaba
// This class is an abstract class for IComponents which take a boolean parameter and store just a boolean field. 
// The abstract class reduces a significant amount of code as many components can be reduced to just getters and setters
// and one boolean input. The class couldn't be genericized because of Java losing the declarations at compile time. 
// This class also is able to fill in the newCopy method using reflection which facilitates the rewind and save features
// and reduces the amount of code that classes extending this abstract class need. The class keeps components consistent easy to
// understand and low in code mass. 
package components;

import java.lang.reflect.Constructor;
import exceptions.CopyException;


public abstract class AbstractBooleanComponent extends AComponent implements IComponent {
	private Boolean myBoolean;

	public AbstractBooleanComponent(boolean bool) {
		setBoolean(bool);
	}
	public AbstractBooleanComponent() {
	}
	
	public Boolean getBoolean() { 
		return myBoolean;
	}

	public void setBoolean(boolean bool) {
		myBoolean = bool;
	}

	@Override
	public IComponent newCopy() throws CopyException {
		Class<? extends AbstractBooleanComponent> c = this.getClass();
		Constructor<? extends AbstractBooleanComponent> ctor = null;
		IComponent myCopy = nullParameterCopy(c,ctor);
		if (myCopy==null){
			myCopy = oneParameterCopy(c,ctor);
		}
		return myCopy;
	}
	
	private IComponent nullParameterCopy(Class<? extends AbstractBooleanComponent> c, Constructor<? extends AbstractBooleanComponent> ctor) throws CopyException{
		if (myBoolean==null){
			try {
				ctor = c.getConstructor();
				return (IComponent) ctor.newInstance();
			} catch (Exception e) {
				throw new CopyException(CopyException.DEAFULT_WARNING);
			}

		}
		return null;
	}
	private IComponent oneParameterCopy(Class<? extends AbstractBooleanComponent> c, Constructor<? extends AbstractBooleanComponent> ctor) throws CopyException{
		if (myBoolean!=null){
			try {
				ctor = c.getConstructor(boolean.class);
				return (IComponent) ctor.newInstance(myBoolean);
			} catch (Exception e) {
				throw new CopyException(CopyException.DEAFULT_WARNING);
			}
		}
		return null;
	}
}
