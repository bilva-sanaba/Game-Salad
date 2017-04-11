package components;

public class TerminalVelComponent implements IComponent{
	
	double terminalX;
	double terminalY;
	
	public TerminalVelComponent(double X, double Y){
		terminalX = X;
		terminalY = Y;
	}
	
	public double getX(){
		return terminalX;
	}
	
	public double getY(){
		return terminalY;
	}
	
	public boolean canAccelerateX(double currentXVelocity){
		return Math.abs(currentXVelocity) <= terminalX;
	}
	
	public boolean canAccelerateY(double currentYVelocity){
		return Math.abs(currentYVelocity) <= terminalY;
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.TerminalVelComponent;
	}

	@Override
	public IComponent newCopy() {
		return new TerminalVelComponent(getX(), getY());
	}
}
