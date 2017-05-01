package data_interfaces;

import java.util.Collection;
import java.util.List;

import entity.Entity;
import entity.IEntityManager;

public interface EngineCommunication {

	public List<IEntityManager> getIEntityManagers();

	public InfiniteEnum getInfinite();
	
	public String getMusic();
	
	public int getLives();
}
