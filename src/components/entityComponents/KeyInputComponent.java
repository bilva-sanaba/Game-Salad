package components.entityComponents;

import java.util.HashMap;
import java.util.Map;

import components.IComponent;
import javafx.scene.input.KeyCode;

public class KeyInputComponent implements IComponent {
	private Map<KeyCode,String> inputMap = new HashMap<KeyCode,String>();
	
	public KeyInputComponent(Map<KeyCode,String> keyMap){
		inputMap=keyMap;
	}
	
	public KeyInputComponent(){
	}
	
	
	public Map<KeyCode,String> getMap(){
		return inputMap;
	}
	
	public void setMap(Map<KeyCode,String> map){
		inputMap=map;
	}
	
	public void addToMap(KeyCode kc, String ke){
		inputMap.put(kc, ke);
	}
	
	public void removeFromMap(KeyCode kc){
		inputMap.remove(kc);
	}
	
	@Override
	public ComponentType getComponentType() {
		return ComponentType.KeyInput;
	}

	@Override
	public IComponent newCopy() {
		return new KeyInputComponent(inputMap);
	}

}
