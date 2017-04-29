package gamedata;

import components.entityComponents.LocationComponent;
import entity.restricted.IRestrictedEntityManager;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.StringProperty;

public interface IRestrictedGameData {
	public ReadOnlyDoubleProperty getPoints();
	
	public ReadOnlyDoubleProperty getLives();

	public IRestrictedEntityManager getRestrictedEntityManager();

	public ReadOnlyDoubleProperty getLevel();

	public LocationComponent getMainLocation();

	public String getMusic();

	public String getAchievement();
}
