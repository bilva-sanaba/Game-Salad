package gameEngine.chooser;

import java.util.Arrays;
import java.util.List;

import data_interfaces.InfiniteEnum;
import engines.AIEngine;
import engines.AbstractEngine;
import engines.CollisionEngine;
import engines.InfiniteEngine;
import engines.InputEngine;
import engines.MovementEngine;
import engines.TimeEngine;
import entity.IEntityManager;

public class GameEngineChooser implements IGameEngineChooser {
	private List<AbstractEngine> myEngines;
	
	public GameEngineChooser(IEntityManager myEntityManager, InfiniteEnum infinite){
		myEngines = Arrays.asList(new InputEngine(myEntityManager), 
				new MovementEngine(myEntityManager), new CollisionEngine(myEntityManager), 
				new TimeEngine(myEntityManager),new AIEngine(myEntityManager));
		if (infinite!=InfiniteEnum.None){
			myEngines.add(new InfiniteEngine(myEntityManager, infinite));
		}
	}
	@Override
	public List<AbstractEngine> getEngines() {
		return myEngines;
	}

}
