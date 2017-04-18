package engines;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import components.IComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.KeyInputComponent;
import components.entityComponents.SpriteComponent;
import components.keyExpressions.ConcreteKeyExpressions;
import components.movementcomponents.AccelerationComponent;
import components.movementcomponents.LocationComponent;
import components.movementcomponents.VelocityComponent;
import entity.Entity;
import entity.IEntity;
import entity.IEntityManager;
import entity.restricted.IRestrictedEntity;
import javafx.scene.input.KeyCode;

public class InputEngine extends AbstractEngine{
	private ScriptEngine engine; 
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
			if (ic.getMap().containsKey(key)){
				if (ic.getMap().get(key)!="JUMP" && ic.getMap().get(key)!="RIGHT" && ic.getMap().get(key)!="LEFT" && ic.getMap().get(key)!="REMOVE" ){
					try {
						VelocityComponent vc = (VelocityComponent) e.getComponent(new VelocityComponent(0,0));
						AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
						engine.put("vc", vc);
						engine.put("ac", ac);
						engine.eval(ic.getMap().get(key));
					} catch (ScriptException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					ConcreteKeyExpressions.valueOf(ic.getMap().get(key)).getKeyExpression().operation(e);
					((IRestrictedEntity) e).changed(e);
				}
				Entity x = new Entity(1000);
				x.addComponent(new LocationComponent(0,100));
				x.addComponent(new SpriteComponent(("dirt.jpg")));

				ImagePropertiesComponent xc = new ImagePropertiesComponent();
				xc.setHeight(50);
				xc.setWidth(50);
				x.addComponent(xc);
			getEManager().changed(x);
			}
		}
		}	
	}
}
