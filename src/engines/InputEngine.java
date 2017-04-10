package engines;

import java.util.Collection;
import java.util.List;

import components.ComponentType;
import components.IComponent;
import components.KeyInputComponent;
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
		return null;
	}
	private void handleInput(IEntity e, Collection<KeyCode> keys){
		KeyInputComponent ic = (KeyInputComponent) e.getComponent(ComponentType.KeyInput);
		for (KeyCode key : keys){
			if (ic.getMap().containsKey(key)){
				ic.getMap().get(key).operation(e);
			}
		}
		
		
		
	}

}
