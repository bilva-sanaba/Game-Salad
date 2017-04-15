package engines;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import components.IComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.KeyInputComponent;
import components.keyExpressions.ConcreteKeyExpressions;
import entity.Entity;
import entity.IEntity;
import entity.IEntityManager;
import javafx.scene.input.KeyCode;

public class InputEngine extends AbstractEngine{

	public InputEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
		
	}

	@Override
	protected List<ComponentType> neededComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<IEntity> update(Collection<KeyCode> keys) {
		for (IEntity e : getEManager().getEntityMap().keySet()){
			handleInput(e,keys);
		}
		return new ArrayList<IEntity>();
	}
	private void handleInput(IEntity e, Collection<KeyCode> keys){
		
		if (e.getComponent(ComponentType.KeyInput)!=null){
		KeyInputComponent ic = (KeyInputComponent) e.getComponent(ComponentType.KeyInput);
		System.out.println(ic.getMap());
		
		for (KeyCode key : keys){
			if (ic.getMap().containsKey(key)){
				System.out.println(key);
				System.out.println("QWPEORIUAJSL;DKFJPOQIEJTAL;SKDFJOIQWEJKTAS;DIFJLOQW;EITJGAS;DF");
				ConcreteKeyExpressions.valueOf(ic.getMap().get(key)).getKeyExpression().operation(e);
			}
		}
		}
		
		
		
	}

}
