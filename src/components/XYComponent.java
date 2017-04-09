package components;

public abstract class XYComponent {
	private int x;
	private int y;
	public XYComponent(int xVal,int yVal){
		x = xVal;
		y = yVal;
	}
	public XYComponent(){}

	public void setX(int newX){
		x = newX;
	}
	public void setY(int newY){
		y = newY;
	}
	
	public void setXY(int newX, int newY){
			x = newX;
			y = newY;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
}
