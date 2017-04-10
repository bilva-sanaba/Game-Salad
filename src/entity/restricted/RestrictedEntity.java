package entity.restricted;

import java.util.Observable;

import java.util.Observer;

import gameView.Coordinate;
import javafx.beans.InvalidationListener;

public class RestrictedEntity extends AbstractRestrictedEntity implements IRestrictedEntity {
	public RestrictedEntity(int id, Coordinate c, String image){
		super(id, c,image);
	}
	@Override
	public Coordinate getLocation() {
		// TODO Auto-generated method stub
		return myCoordinate;
	}

	@Override
	public String getImagePath() {
		// TODO Auto-generated method stub
		return imagePath;
	}
	
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		//IN THIS METHOD YOU WILL CALL THE APPROPRIATE UPDATE FOR MOVEMENT, ETC.
	}
	
	@Override
	public void addObserver(Observer obs) {
		this.addObserver(obs);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return myID;
	}
}
