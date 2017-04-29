package data_interfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;

import entity.*;

public class Communicator extends GameSavingDataTool implements EngineCommunication, PlayerCommunication {

	private String fileName;
	List<Map> results;

	public Communicator(String s) {
		fileName = getPrefix() + s + getSuffix();
		XMLPlacedParser xp = new XMLPlacedParser();
		results = xp.getData(fileName);
	}

	
	
	public List<IEntityManager> getIEntityManagers() {
		Map <Integer, Map<Integer, Entity>> m = results.get(0);
		List <IEntityManager> ret = new ArrayList<IEntityManager>();
		List <Entity> toBeAdded;
		
		for (int i = 1; i <= m.size(); i++) {
			toBeAdded = new ArrayList<Entity>();
			for (Integer j: m.get(i).keySet()) {
				toBeAdded.add(m.get(i).get(j));
			}
			ret.add(new EntityManager(toBeAdded));
		}
		
		return ret;
	}



	@Override
	public List<LevelEntity> getLevelEntities() {
		Map <Integer, LevelEntity> m = results.get(1);
		List<LevelEntity> ret = new ArrayList<LevelEntity>();
		
		for (int i = 1; i < m.size(); i++) {
			ret.add(m.get(i));
		}
		return ret;
	}



	@Override
	public SplashEntity getSplashEntity() {
		return (SplashEntity) results.get(2).get(getSplashConstant());
	}
}
