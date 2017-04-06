package entitiy.restricted;

import java.util.Observer;

import gameView.Coordinate;
import javafx.beans.InvalidationListener;

public class RestrictedEntity extends AbstractRestrictedEntity implements IRestrictedEntity {
	public RestrictedEntity(Coordinate c, String image){
		super(c,image);
	}

	@Override
	public Coordinate getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getImagePath() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void addObserver(Observer obs) {
		this.addObserver(obs);
	}

}
