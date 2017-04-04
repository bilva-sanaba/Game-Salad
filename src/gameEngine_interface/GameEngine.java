package gameEngine_interface;

import java.util.List;

import engines.AbstractEngine;
import entity.IEntityManager;

public class GameEngine implements GameEngineInterface {
	private IEntityManager myEntityManager; 
	List<AbstractEngine> myEngines;
	@Override
	public void handleUpdates() {
		for (AbstractEngine s : myEngines){
			s.update();
		}	
	}
}
