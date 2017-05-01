package components.entityComponents;

import components.IComponent;

public class ControllableComponent implements IComponent{
	
	private boolean controllable = true;
	public ControllableComponent(boolean c){
		controllable = c;
	}
	
	public ControllableComponent(){
		super();
	}
	
	@Override
	public ComponentType getComponentType() {
		return ComponentType.Controllable;
	}

	@Override
	public IComponent newCopy() {
		ControllableComponent myControllableComponent = new ControllableComponent(true);
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
