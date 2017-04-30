package gamedata;

import components.entityComponents.LocationComponent;
import entity.restricted.IRestrictedEntityManager;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;

public interface IGameData extends IRestrictedGameData{

	public void setPoints(double d);

	public void setLives(double d);

	public void setRestrictedEntityManager(IRestrictedEntityManager rem);

	public void setLevel(double d);

	public void setMainLocation(LocationComponent lc);

	public void setMusic(String s);
	
	public void setAchievement(String ac);

}
