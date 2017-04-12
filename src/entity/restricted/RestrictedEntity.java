package entity.restricted;

import java.util.Observable;

import java.util.Observer;

import components.entityComponents.ImagePropertiesComponent;
import gameView.Coordinate;
import javafx.beans.InvalidationListener;

public class RestrictedEntity extends AbstractRestrictedEntity implements IRestrictedEntity {
	public RestrictedEntity(int id, Coordinate c, String image, ImagePropertiesComponent comp){
		super(id, c, image, comp);
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
	
	public ImagePropertiesComponent getIPComponent(){
		return iPComponent;
	}
	
	public double getImageHeight(){
		return getIPComponent().getHeight();
	}
	
	public double getImageWidth(){
		return getIPComponent().getWidth();
	}
}
