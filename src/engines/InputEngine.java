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
//					if (ic.getGroovyMap().containsKey(key)){
//						try {
//							vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
//							ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
//							engine.put("vc", vc);
//							engine.put("ac", ac);
//							engine.eval(ic.getGroovyMap().get(key));
//						} catch (ScriptException e1) {
//							GroovyAlert alert = new GroovyAlert("Invalid Author Key Action", "This program crashed due to an incorrect expression written by the author");
//						}
//					}
				}
			}
		}
		
		return rgd;	
	}
}
