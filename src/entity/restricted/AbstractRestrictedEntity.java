package entity.restricted;

import gameView.Coordinate;

public abstract class AbstractRestrictedEntity {

	protected int myID;
	protected Coordinate myCoordinate;
	protected String imagePath;

	public AbstractRestrictedEntity(int id, Coordinate c, String image) {
		myID = id;
		myCoordinate = c ; 
		imagePath = image; 
	}
}