package engines;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import actions.BlockTopRegularCollision;
import actions.BounceOffBlockSide;
import alerts.GroovyAlert;
import components.IComponent;
import components.LocationComponent;
import components.collisionComponents.CollisionComponentType;
import components.collisionComponents.CollisionComponentsHandler;
import components.collisionComponents.SideCollisionComponent;
import components.entityComponents.CollidableComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.KeyInputComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.SpriteComponent;
import components.keyExpressions.ConcreteKeyExpressions;
import components.movementcomponents.AccelerationComponent;
import components.movementcomponents.VelocityComponent;
import entity.Entity;
import entity.IEntity;
import entity.IEntityManager;
import entity.presets.AbstractBlock;
import entity.restricted.IRestrictedEntity;
import javafx.scene.input.KeyCode;

public class InputEngine extends AbstractEngine{
	private ScriptEngine engine; 
	private boolean x = true;
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
		for (IEntity e : getEManager().getEntities()){
			handleInput(e,keys);
		}
	}
	private void handleInput(IEntity e, Collection<KeyCode> keys){
		if (e.getComponent(ComponentType.KeyInput)!=null){
		KeyInputComponent ic = (KeyInputComponent) e.getComponent(ComponentType.KeyInput);
		
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
					ic.getActionMap().get(key).executeAction(e, null, getEManager());
					((IRestrictedEntity) e).changed(e);
				}
			if (ic.getGroovyMap().containsKey(key)){
				try {
					VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
					AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
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
