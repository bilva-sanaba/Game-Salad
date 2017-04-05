package components;

public class LocationComponent implements IComponent{
	private int xLocation;
	private int yLocation;
	public LocationComponent(int x,int y){
		xLocation = x;
		yLocation = y;
	}
	public LocationComponent(){}
	@Override
	public ComponentType getComponentType() {
		return ComponentType.Location;
	}
	public void setX(int newX){
		xLocation = newX;
	}
	public void setY(int newY){
		yLocation = newY;
	}
	public int getX(){
		return xLocation;
	}
	public int getY(){
		return yLocation;
	}
}
