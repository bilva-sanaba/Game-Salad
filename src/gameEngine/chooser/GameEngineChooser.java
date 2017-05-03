package gameEngine.chooser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import engines.AIEngine;
import engines.AbstractEngine;
import engines.CollisionEngine;
import engines.IEngine;
import engines.InfiniteEngine;
import engines.InputEngine;
import engines.MovementEngine;
import engines.TimeEngine;
import engines.infinite.InfiniteEnum;
import entity.IEntityManager;

public class GameEngineChooser implements IGameEngineChooser {
	private List<IEngine> myEngines;
	
	public GameEngineChooser(IEntityManager myEntityManager, InfiniteEnum infinite){
		myEngines = new ArrayList<IEngine>();
		
		myEngines.add(new InputEngine(myEntityManager));
		myEngines.add(new MovementEngine(myEntityManager));
		myEngines.add(new CollisionEngine(myEntityManager));
		myEngines.add(new TimeEngine(myEntityManager));
		myEngines.add(new AIEngine(myEntityManager));
		
		if (infinite != null && infinite!=InfiniteEnum.None){
			myEngines.add(new InfiniteEngine(myEntityManager, infinite));
		}
	}
	@Override
	public List<IEngine> getEngines() {
		return myEngines;
	}

}
