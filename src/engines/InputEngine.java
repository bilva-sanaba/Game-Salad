package engines;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import actions.BlockTopRegularCollision;
import alerts.GroovyAlert;
import components.IComponent;
import components.entityComponents.AccelerationComponent;
import components.entityComponents.CollidableComponent;
import components.entityComponents.CollisionComponentType;
import components.entityComponents.CollisionComponentsHandler;
import components.entityComponents.ComponentType;
import components.entityComponents.ConcreteKeyExpressions;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.KeyInputComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.SideCollisionComponent;
import components.entityComponents.SpriteComponent;
import components.entityComponents.VelocityComponent;
import entity.Entity;
import entity.IEntity;
import entity.IEntityManager;
import entity.presets.AbstractBlock;
import entity.restricted.IRestrictedEntity;
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;

public class InputEngine extends AbstractEngine{
	private ScriptEngine engine; 
	private boolean x = true;
	private Collection<IRestrictedEntity> newEntities = new ArrayList<IRestrictedEntity>();
	public InputEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
		engine = new ScriptEngineManager().getEngineByName("groovy");	
	}
	

	@Override
	protected List<ComponentType> neededComponents() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IRestrictedGameData update(Collection<KeyCode> keys, IRestrictedGameData gameData) {
		newEntities.clear();
		for (IEntity e : getEManager().getEntities()){
			handleInput(e,keys, gameData);
		}
		for (IRestrictedEntity e : newEntities){
			IEntity myE = e.clone();
			getEManager().getEntities().add(myE);
			getEManager().changed(myE);
		}
		return gameData;
	}
	private void handleInput(IEntity e, Collection<KeyCode> keys, IRestrictedGameData gameData){
		if (e.getComponent(ComponentType.KeyInput)!=null){
			
			KeyInputComponent ic = (KeyInputComponent) e.getComponent(ComponentType.KeyInput);
			
			VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity); 
			AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
			
			//TODO: FIND A WAY TO INCORPORATE FRICTION
			//FrictionComponent fc = (FrictionComponent) e.getComponent(ComponentType.Friction);
			
			//Handles Decelerating
			if(vc.getX()!= 0 && keys.isEmpty()){
				if(vc.getX() > 0.5){
					ac.setX(-0.2);
				}
				else if (vc.getX() < -0.5){
					ac.setX(0.2);
				}
				else if (Math.abs(vc.getX()) < 0.3){
					ac.setX(0);
					vc.setX(0);
				}
				return;
			}
			
		ic = (KeyInputComponent) e.getComponent(ComponentType.KeyInput);
		
		for (KeyCode key : keys){
			if (ic.getActionMap().containsKey(key)){
					newEntities.addAll(ic.getActionMap().get(key).executeAction(e, null, getEManager(), gameData).getRestrictedEntityManager().getRestrictedEntities());
					((IRestrictedEntity) e).changed(e);
				}
			if (ic.getGroovyMap().containsKey(key)){
				try {
					vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
					ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
					engine.put("vc", vc);
					engine.put("ac", ac);
					engine.eval(ic.getGroovyMap().get(key));
				} catch (ScriptException e1) {
					GroovyAlert alert = new GroovyAlert("Invalid Author Key Action", "This program crashed due to an incorrect expression written by the author");
				}
			}
			}
//		}
		}	
	}
}
