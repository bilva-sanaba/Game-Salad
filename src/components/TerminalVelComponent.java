package components;

public class TerminalVelComponent{
	
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
		return currentXVelocity < terminalX;
	}
	
	public boolean canAccelerateY(double currentYVelocity){
		return currentYVelocity < terminalY;
	}
}
