// This entire file is part of my masterpiece
// BELAL TAHER
// The reason I added this code to my master piece is very similar to why I added the movement engine.
// I think the way that it maps key input to an action is extremely good design because it makes 
// changing key input extremely easy. All you have to do is locate where in the program that action
// that the key is mapped to and change values in the class. Both this class and my movement engine class
// display my understanding of flexible code because they both allow users to edit very easily to achieve
// desired functionality. I didn't refactor this code after the project's deadline either, but I refactored
// it significantly near the end of the project so that it would be a lot easier to add all the random
// functionality that we started to pour into the program near the end. 

package engines;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import actions.IAction;
import alerts.GroovyAlert;
import components.entityComponents.AccelerationComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.ControllableComponent;
import components.entityComponents.KeyInputComponent;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import entity.restricted.IRestrictedEntity;
import gamedata.GameDataFactory;
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;
/**
 * Engine which checks which relevant keys are being pressed and then runs their mapped actions 
 * in the key input component. Commented code can also handle mapped strings and run groovy actions but lowers
 * run time
 * @author Bilva, Belal
 *
 */

public class InputEngine extends AbstractEngine {
	private ScriptEngine engine; 
	private Collection<IRestrictedEntity> newEntities = new ArrayList<IRestrictedEntity>();
	public InputEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
		engine = new ScriptEngineManager().getEngineByName("groovy");	
	}

	@Override
	public IRestrictedGameData update(Collection<KeyCode> keys, IRestrictedGameData gameData) {
		newEntities.clear();
		IRestrictedGameData rgd = new GameDataFactory().blankEntityData(gameData);
		for (IEntity e : getEManager().getEntities().toArray(new IEntity[getEManager().getEntities().size()])){
			rgd = handleInput(e,keys, rgd);
		}
		for (IRestrictedEntity e : newEntities){
			IEntity myE = e.clone();
			getEManager().getEntities().add(myE);
			getEManager().changed(myE);
		}
		return rgd;
	}
	
	private IRestrictedGameData handleInput(IEntity e, Collection<KeyCode> keys, IRestrictedGameData gameData){
		IRestrictedGameData rgd = gameData;
		if (e.hasComponent(ComponentType.KeyInput)){
			ControllableComponent cc = (ControllableComponent) e.getComponent(ComponentType.Controllable);
			KeyInputComponent ic = (KeyInputComponent) e.getComponent(ComponentType.KeyInput);
			VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity); 
			AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);	
			ic = (KeyInputComponent) e.getComponent(ComponentType.KeyInput);
			if(cc!=null && cc.checkControl() == true){	
				for (KeyCode key : keys){
					if (ic.getActionMap().containsKey(key)){
						for (IAction action : ic.getActionMap().get(key)){
							rgd = action.executeAction(e, null, getEManager(), rgd);
							newEntities.addAll(rgd.getRestrictedEntityManager().getRestrictedEntities());
							((IRestrictedEntity) e).changed(e);
						}
					}
				}
			}
		}
		
		return rgd;	
	}
}
