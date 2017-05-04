package engines;

import java.util.Collection;
import java.util.Map;

import actions.IAction;
import components.entityComponents.ComponentType;
import components.entityComponents.TimeComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;

public class TimeEngine extends AbstractEngine{ 
	public TimeEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
	}

	@Override
	public IRestrictedGameData update(Collection<KeyCode> keysPressed, IRestrictedGameData gameData) {
		IRestrictedGameData rgd = gameData;
		for (IEntity e : getEManager().getEntities().toArray(new IEntity[getEManager().getEntities().size()])){
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
						rgd = action.executeAction(e, null, getEManager(), rgd);
						lastTime.put(action, (int) System.currentTimeMillis());
					}
				}
				for (IAction action : singleTime.keySet().toArray(new IAction[singleTime.keySet().size()])){
					if (!lastTime.containsKey(action)){
						lastTime.put(action,(int) System.currentTimeMillis());
					}
					if ((int) System.currentTimeMillis()-lastTime.get(action)> singleTime.get(action) ){
						rgd = action.executeAction(e, null, getEManager(), rgd);
						singleTime.remove(action);
					}
				}
			}
		}
		return rgd;
	}

}
