package gameView;

import gameView_interfaces.UIImagePropertyInterface;

public class UIImageProperty implements UIImagePropertyInterface {

	private Coordinate myLocation;
	private String myType;

	public UIImageProperty(Coordinate coord, String type) {
		myLocation = coord;
		myType = type;
	}

	@Override
	public Coordinate getLocation() {
		return myLocation;
	}

	@Override
	public double getXDirection() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getYDirection() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType() {
		return myType;
	}

}
