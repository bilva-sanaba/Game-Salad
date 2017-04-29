package gamedata;

import components.entityComponents.LocationComponent;
import entity.restricted.IRestrictedEntityManager;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;

public interface IRestrictedGameData {
	public ReadOnlyDoubleProperty getPoints();
	
	public ReadOnlyIntegerProperty getLives();

	public IRestrictedEntityManager getRestrictedEntityManager();

	public ReadOnlyIntegerProperty getLevel();

	public LocationComponent getMainLocation();

	public String getMusic();

	public String getAchievement();
}
