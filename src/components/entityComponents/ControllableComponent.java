package components.entityComponents;

import components.IComponent;

public class ControllableComponent implements IComponent{
	
	private boolean controllable = true;

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Controllable;
	}

	@Override
	public IComponent newCopy() {
		ControllableComponent myControllableComponent = new ControllableComponent();
		if(!(controllable)){
			myControllableComponent.loseControl();
		}
		return myControllableComponent;
	}
	
	public void loseControl(){
		controllable = false;
	}
	
	public void regainControl(){
		controllable = true;
	}
	
	public boolean checkControl(){
		return controllable;
	}

}
