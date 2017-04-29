package engines;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import actions.IAction;
import components.entityComponents.ComponentType;
import components.entityComponents.TimeComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;

public class TimeEngine extends AbstractEngine { 
	public TimeEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
	}

	@Override
	protected List<ComponentType> neededComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override

	public IRestrictedGameData update(Collection<KeyCode> keysPressed, IRestrictedGameData gameData) {
		for (IEntity e : getEManager().getEntities()){
			TimeComponent tc = (TimeComponent) e.getComponent(ComponentType.Time);
			if (tc!=null){
				Map<IAction,Integer> lastTime = tc.getLastTime(); 
				Map<IAction,Integer> constantTime = tc.getConstantTime();
				Map<IAction,Integer> singleTime =tc.getSingleTime();
				for (IAction action : constantTime.keySet()){
					if (!lastTime.containsKey(action)){
						lastTime.put(action,(int) System.currentTimeMillis());
					}
					if ((int) System.currentTimeMillis()-lastTime.get(action)> constantTime.get(action) ){
						action.executeAction(e, null, getEManager(), gameData);
						lastTime.put(action, (int) System.currentTimeMillis());
					}
				}
				for (IAction action : singleTime.keySet()){
					if (!lastTime.containsKey(action)){
						lastTime.put(action,(int) System.currentTimeMillis());
					}
					if ((int) System.currentTimeMillis()-lastTime.get(action)> singleTime.get(action) ){
						action.executeAction(e, null, getEManager(), gameData);
						singleTime.remove(action);
					}
				}
			}
		}
		return gameData;
	}

}
