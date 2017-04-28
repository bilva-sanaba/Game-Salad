package components.entityComponents;

import components.IComponent;

public class DamagedComponent implements IComponent{

	private boolean damaged = false;
	
	
	
	@Override
	public ComponentType getComponentType() {
		return ComponentType.Damaged;
	}

	@Override
	public IComponent newCopy() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setDamaged(){
		damaged = true;
	}
	
	public void notDamaged(){
		damaged = false;
	}
	
	

}
