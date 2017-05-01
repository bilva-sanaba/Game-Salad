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
		System.out.println("COMMUNICATOR MADE: " + s);
		fileName = s;
		XMLPlacedParser xp = new XMLPlacedParser();
		results = xp.getData(fileName);
	}
	
	
	public List<IEntityManager> getIEntityManagers() {
		Map <Integer, Map<Integer, Entity>> m = results.get(0);
		List <IEntityManager> ret = new ArrayList<IEntityManager>();
		List <IEntity> toBeAdded;
		
		System.out.println("m size: " + m.size());
		
		for (int i = 1; i <= m.size(); i++) {
			System.out.println("THISSSSS happens");
			toBeAdded = new ArrayList<IEntity>();
			for (Integer j: m.get(i).keySet()) {
				toBeAdded.add(m.get(i).get(j));
				System.out.println("this happens");
			}
			ret.add(new EntityManager(toBeAdded));
			
		}
		
		return ret;
	}



	@Override
	public List<LevelEntity> getLevelEntities() {
		Map <Integer, LevelEntity> m = results.get(1);
		List<LevelEntity> ret = new ArrayList<LevelEntity>();
		
		for (int i = 1; i <= m.size(); i++) {
			ret.add(m.get(i));
		}
		System.out.println("HOW MANY LEVEL ENTITIES" + ret.size());
		return ret;
	}



	@Override
	public SplashData getSplashEntity() {
		return (SplashData) results.get(2).get(getSplashConstant());
	}



	@Override
	public InfiniteEnum getInfinite() {
		Map<Integer, LevelEntity> m = results.get(1);
		System.out.println(m.get(1).getInfiniteEnum());
		return m.get(1).getInfiniteEnum();
	}
}
