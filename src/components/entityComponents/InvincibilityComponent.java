package components.entityComponents;

import components.AComponent;
import components.IComponent;

public class InvincibilityComponent extends AComponent implements IComponent{
	private boolean invincible;
	public InvincibilityComponent(boolean b){
		invincible = b;
	}
	@Override
	public ComponentType getComponentType() {
		// TODO Auto-generated method stub
		return ComponentType.Invincible;
	}
	@Override
	public IComponent newCopy() {
		// TODO Auto-generated method stub
		return new InvincibilityComponent(invincible);
	}
	public boolean getBoolean(){
		return invincible;
	}
	public void setBoolean(boolean b){
		invincible = b;
	}
}
