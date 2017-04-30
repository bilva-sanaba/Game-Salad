package data_interfaces;

import java.util.List;

import entity.LevelEntity;
import entity.SplashData;

public interface PlayerCommunication {

	public List<LevelEntity> getLevelEntities();
	
	public SplashData getSplashEntity();
}
