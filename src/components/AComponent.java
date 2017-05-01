package components;

import components.entityComponents.ComponentType;

public abstract class AComponent implements IComponent {

	@Override
	public abstract ComponentType getComponentType();

	@Override
	public abstract IComponent newCopy();
	
	@Override
	public int hashCode(){
		System.out.println(getComponentType());
		return getComponentType().toString().hashCode();
	}
	
	public boolean equals(Object o){
		return o.hashCode() == this.hashCode();
	}

}
