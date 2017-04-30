package gamedata;

import components.entityComponents.LocationComponent;
import entity.restricted.IRestrictedEntityManager;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyStringProperty;

public interface IRestrictedGameData {
	public ReadOnlyDoubleProperty getPoints();
	
	public ReadOnlyIntegerProperty getLives();

	public IRestrictedEntityManager getRestrictedEntityManager();

	public ReadOnlyIntegerProperty getLevel();

	public LocationComponent getMainLocation();

	public ReadOnlyStringProperty getMusic();

	public String getAchievement();
}
