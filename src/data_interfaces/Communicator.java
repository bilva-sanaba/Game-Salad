// This entire file is part of my masterpiece.
// Josh Kopen

package data_interfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import engines.infinite.InfiniteEnum;
import entity.Entity;
import entity.EntityManager;
import entity.IEntity;
import entity.IEntityManager;
import entity.LevelEntity;
import entity.SplashData;

/**
 * I believe that this file now represents good design as it utilizes the GameSavingDataTool to reduce redundancies
 * with LoadEvent, has improved variable names, and is overall well written code. I believe that I have used
 * inheritance well in this situation as well as a good use of interfaces, only giving Engine and PLayer the methods
 * they specifically need but all coming from a single instantiated object as to not do the work twice.
 * 
 * @author joshuakopen
 *
 */

public class Communicator extends GameSavingDataTool implements EngineCommunication, PlayerCommunication {

	private String fileName;
	Map<Class<? extends Entity>, Map> dataMap;

	public Communicator(String s) {
		fileName = s;
		XMLPlacedParser xp = new XMLPlacedParser();
		dataMap = xp.getData(fileName);
	}
	
	public List<IEntityManager> getIEntityManagers() {
		Collection <IEntity> addList;
		List <IEntityManager> retList = new ArrayList<IEntityManager>();
		Function f = getFunctionMap().get(Entity.class);
		Map<Integer, Map<Integer, Entity>> retMap = (Map<Integer, Map<Integer, Entity>>) f.apply(dataMap);
		
		for (Integer i: retMap.keySet()) {
			addList = new ArrayList<IEntity>();
			for (Integer j: retMap.get(i).keySet()) {
				addList.add(retMap.get(i).get(j));
			}
			retList.add(new EntityManager(addList));
		}
		return retList;
	}

	@Override
	public List<LevelEntity> getLevelEntities() {
		Map <Integer, LevelEntity> m = getLevelMap();
		List<LevelEntity> ret = new ArrayList<LevelEntity>();
		
		for (int i = 1; i <= m.size(); i++) {
			ret.add(m.get(i));
		}
		return ret;
	}

	@Override
	public SplashData getSplashData() {
		Function f = getFunctionMap().get(SplashData.class);
		return (SplashData) f.apply(dataMap);
	}

	@Override
	public InfiniteEnum getInfinite() {
		Map<Integer, LevelEntity> m = getLevelMap();
		return m.get(getStorageLevel()).getInfiniteEnum();
	}

	public String getMusic() {
		Map<Integer, LevelEntity> m = getLevelMap();
		return m.get(getStorageLevel()).getMusic();
	}
	
	public int getLives() {
		Map <Integer, LevelEntity> m = getLevelMap();
		return m.get(getStorageLevel()).getLives();
	}

	@Override
	public boolean getCameraOn() {
		Map<Integer, LevelEntity> m = getLevelMap();
		return m.get(getStorageLevel()).getCamera();
	}
	
	private Map<Integer, LevelEntity> getLevelMap() {
		Function f = getFunctionMap().get(LevelEntity.class);
		return (Map<Integer, LevelEntity>) f.apply(dataMap);
	}
}