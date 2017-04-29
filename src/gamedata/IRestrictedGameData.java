package gamedata;

import components.entityComponents.LocationComponent;
import entity.restricted.IRestrictedEntityManager;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;

public interface IRestrictedGameData {
	public double getPoints();
	
	public double getLives();

	public IRestrictedEntityManager getRestrictedEntityManager();

	public double getLevel();

	public LocationComponent getMainLocation();

	public String getMusic();
}
