package components;

public class FrictionComponent implements IComponent{
	
	private double friction;
	
	public FrictionComponent(double frictionValue){
		friction = frictionValue;
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.FrictionComponent;
	}

	@Override
	public IComponent newCopy() {
		return new FrictionComponent(friction);
	}

}
