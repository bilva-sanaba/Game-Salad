package components;

public abstract class XYComponent extends AComponent {
	private double x;
	private double y;

	public XYComponent(double xVal, double yVal) {
		x = xVal;
		y = yVal;
	}
	
	public XYComponent(int xVal, int yVal) {
		x = xVal;
		y = yVal;
	}

	public XYComponent() {
	}

	public void setX(double newX) {
		x = newX;
	}

	public void setY(double newY) {
		y = newY;
	}

	public void setXY(double newX, double newY) {
		x = newX;
		y = newY;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
