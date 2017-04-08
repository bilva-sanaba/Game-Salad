package entitiy.restricted;

import java.util.Observable;

import gameView.Coordinate;
import javafx.beans.InvalidationListener;

public class RestrictedEntity extends AbstractRestrictedEntity implements IRestrictedEntity {
	public RestrictedEntity(Coordinate c, String image){
		super(c,image);
	}
	/*@Override
	public void addListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}*/

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
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		//IN THIS METHOD YOU WILL CALL THE APPROPRIATE UPDATE FOR MOVEMENT, ETC.
	}

}
