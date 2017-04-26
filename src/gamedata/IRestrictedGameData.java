package gamedata;

import components.entityComponents.LocationComponent;
import entity.restricted.IRestrictedEntityManager;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;

public interface IRestrictedGameData {
	public DoubleProperty getPoints();
	
	public DoubleProperty getLives();

	public IRestrictedEntityManager getRestrictedEntityManager();

	public DoubleProperty getLevel();

	public LocationComponent getMainLocation();

	public StringProperty getMusic();
	
	public GameData mergeWith(IRestrictedGameData merger);
}
