package gameEngine_interface;

import java.util.List;

import data_interfaces.XMLParser;
import engines.AbstractEngine;
import entitiy.restricted.IRestrictedEntityManager;
import entity.IEntityManager;
/**
 * Basic GameEngine class
 * Note: the engines must be created in someway, likely via reflection
 * @author Bilva
 *
 */
public class GameEngine implements GameEngineInterface {
	private ILevelManager myLevelManager;
	private IEntityManager myEntityManager; 
	private List<AbstractEngine> myEngines;
	private XMLParser myParser = new XMLParser();
	GameEngine(String xmlDataFile){
		myLevelManager = XMLParser.createLevelManager();
	}
	/**
	 * Runs each Engine in my Engine
	 */
	@Override
	public void handleUpdates() {
		for (AbstractEngine s : myEngines){
			s.update();
		}	
	}
	@Override
	public IRestrictedEntityManager getEntities() {
		// TODO Auto-generated method stub
		return null;
	}
}
