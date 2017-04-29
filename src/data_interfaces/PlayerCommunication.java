package data_interfaces;

import java.util.List;

import entity.LevelEntity;
import entity.SplashEntity;

public interface PlayerCommunication {

	public List<LevelEntity> getLevelEntities();
	
	public SplashEntity getSplashEntity();
}
