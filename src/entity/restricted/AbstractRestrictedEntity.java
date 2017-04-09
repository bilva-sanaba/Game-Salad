package entity.restricted;

import gameView.Coordinate;

public abstract class AbstractRestrictedEntity {

	protected Coordinate myCoordinate;
	protected String imagePath;

	public AbstractRestrictedEntity(Coordinate c, String image) {
		myCoordinate = c ; 
		imagePath = image; 
	}
}