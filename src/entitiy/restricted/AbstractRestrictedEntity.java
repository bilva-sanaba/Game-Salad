package entitiy.restricted;

import java.util.Observable;

import gameView.Coordinate;

public abstract class AbstractRestrictedEntity extends Observable {

	protected Coordinate myCoordinate;
	protected String imagePath;

	public AbstractRestrictedEntity(Coordinate c, String image) {
		myCoordinate = c ; 
		imagePath = image; 
	}
}