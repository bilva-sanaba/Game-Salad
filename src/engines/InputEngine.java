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
import javafx.scene.input.KeyCode;

public class InputEngine extends AbstractEngine{
	private ScriptEngine engine; 
	private boolean x = true;
	private Collection<IEntity> newEntities = new ArrayList<IEntity>();
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
	public void update(Collection<KeyCode> keys) {
		newEntities.clear();
		for (IEntity e : getEManager().getEntities()){
			handleInput(e,keys);
		}
		for (IEntity e : newEntities){
			getEManager().getEntities().add(e);
			getEManager().changed(e);
		}
	}
	private void handleInput(IEntity e, Collection<KeyCode> keys){
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
//				if (ic.getMap().get(key)!="JUMP" && ic.getMap().get(key)!="RIGHT" && ic.getMap().get(key)!="LEFT" && ic.getMap().get(key)!="REMOVE" ){
//					try {
//						VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
//						AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
//						engine.put("vc", vc);
//						engine.put("ac", ac);
//						engine.eval(ic.getMap().get(key));
//					} catch (ScriptException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}else{
//					ConcreteKeyExpressions.valueOf(ic.getMap().get(key)).getKeyExpression().operation(e);
					newEntities.addAll(ic.getActionMap().get(key).executeAction(e, null, getEManager()));
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
