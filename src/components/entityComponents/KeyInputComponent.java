package components.entityComponents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import actions.IAction;
import components.AComponent;
import components.IComponent;
import javafx.scene.input.KeyCode;

public class KeyInputComponent extends AComponent implements IComponent {
	private Map<KeyCode, ArrayList<IAction>> inputMap = new HashMap<KeyCode,ArrayList<IAction>>();
	private Map<KeyCode,String>  groovyMap = new HashMap<KeyCode,String>();
	public KeyInputComponent(Map<KeyCode,ArrayList<IAction>> keyMap, Map<KeyCode,String> stringMap){
		inputMap=keyMap;
		groovyMap = stringMap;
	}
	public KeyInputComponent(Map<KeyCode,ArrayList<IAction>> keyMap){
		inputMap=keyMap;
	}
	
	public KeyInputComponent(){
	}
	
	
	public Map<KeyCode,ArrayList<IAction>> getActionMap(){
		return inputMap;
	}
	public Map<KeyCode,String> getGroovyMap(){
		return groovyMap;
	}
	
//	public void setMap(Map<KeyCode,IAction> map){
//		inputMap=map;
//	}
//	public void setMap(Map<KeyCode,String> map){
//		groovyMap= map;
//	}
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
		if (inputMap.containsKey(kc)){
			inputMap.remove(kc);
		}
		if (groovyMap.containsKey(kc)){
			groovyMap.remove(kc);
		}
	}
	
	@Override
	public ComponentType getComponentType() {
		return ComponentType.KeyInput;
	}

	@Override
	public IComponent newCopy() {
		return new KeyInputComponent(inputMap,groovyMap);
	}

}
