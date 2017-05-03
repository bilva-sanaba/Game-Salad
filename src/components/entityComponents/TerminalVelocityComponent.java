package components.entityComponents;

import components.IComponent;
import components.XYComponent;

public class TerminalVelocityComponent extends XYComponent implements IComponent{

	public TerminalVelocityComponent(double x, double y) {
		super(x, y);
	}

	public TerminalVelocityComponent(int x, int y) {
		super(x, y);
	}
	
	@Override
	public ComponentType getComponentType() {
		return ComponentType.TerminalVelocity;
	}

	@Override
	public IComponent newCopy() {
		// TODO Auto-generated method stub
		return new TerminalVelocityComponent(getX(), getY());
	}

}
