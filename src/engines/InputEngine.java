package engines;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import components.IComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.KeyInputComponent;
import components.keyExpressions.ConcreteKeyExpressions;
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
				ConcreteKeyExpressions.valueOf(ic.getMap().get(key)).getKeyExpression().operation(e);
				((IRestrictedEntity) e).changed(e);
			}
		}
		}
		
		
		
	}

}
