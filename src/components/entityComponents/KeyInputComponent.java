package components.entityComponents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import actions.IAction;
import components.AComponent;
import components.IComponent;
import javafx.scene.input.KeyCode;
/**
 * Component used by the Input engine to run actions if certain mapped inputs are present as detected by the 
 * game player. Also allows for run actions to be groovy string inputs.
 * @author Bilva
 *
 */
public class KeyInputComponent extends AComponent implements IComponent {
	private Map<KeyCode, List<IAction>> inputMap = new HashMap<KeyCode,List<IAction>>();
	private Map<KeyCode,String>  groovyMap = new HashMap<KeyCode,String>();
	public KeyInputComponent(Map<KeyCode,List<IAction>> keyMap, Map<KeyCode,String> stringMap){
		inputMap=keyMap;
		groovyMap = stringMap;
	}
	public KeyInputComponent(Map<KeyCode,List<IAction>> keyMap){
		inputMap=keyMap;
	}
	
	public KeyInputComponent(){
	}
	
	
	public Map<KeyCode,List<IAction>> getActionMap(){
		return inputMap;
	}
	public Map<KeyCode,String> getGroovyMap(){
		return groovyMap;
	}

	public void addToMap(KeyCode kc, IAction ke){
		if (inputMap.containsKey(kc)){
		inputMap.get(kc).add(ke);
		}else{
			ArrayList<IAction> list = new ArrayList<IAction>();
			list.add(ke);
			inputMap.put(kc, list);
		}
	}
	public void addToMap(KeyCode kc, String s){
		groovyMap.put(kc, s);
	}
	
	public void removeFromMap(KeyCode kc){
		mapSafeRemove(kc,inputMap);
		mapSafeRemove(kc,groovyMap);
	}
	
	@Override
	public ComponentType getComponentType() {
		return ComponentType.KeyInput;
	}

	@Override
	public IComponent newCopy() {
		return new KeyInputComponent(inputMap,groovyMap);
	}
	private void mapSafeRemove(KeyCode kc, Map map){
		if (map.containsKey(kc)){
			map.remove(kc);
		}
	}

}
