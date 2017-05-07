package components;

public abstract class AComponent implements IComponent {
	
	@Override
	public int hashCode(){
		return getComponentType().toString().hashCode();
	}
	
	public boolean equals(Object o){
		return o.hashCode() == this.hashCode();
	}

}
