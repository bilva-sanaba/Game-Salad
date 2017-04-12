package entity.restricted;

import java.util.Observable;

import components.entityComponents.ImagePropertiesComponent;
import gameView.Coordinate;

public abstract class AbstractRestrictedEntity extends Observable {

	protected int myID;
	protected Coordinate myCoordinate;
	protected String imagePath;
	protected ImagePropertiesComponent iPComponent;

	public AbstractRestrictedEntity(int id, Coordinate c, String image, ImagePropertiesComponent comp) {
		myID = id;
		myCoordinate = c;
		imagePath = image;
		iPComponent = comp;
	}
}