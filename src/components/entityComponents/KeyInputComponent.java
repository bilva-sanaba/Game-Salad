package components.entityComponents;

import java.util.HashMap;
import java.util.Map;

import components.IComponent;
import javafx.scene.input.KeyCode;

public class KeyInputComponent implements IComponent {
	private Map<KeyCode,KeyExpression> inputMap = new HashMap<KeyCode,KeyExpression>();
	
	public KeyInputComponent(Map<KeyCode,KeyExpression> keyMap){
		inputMap=keyMap;
	}
	
	public KeyInputComponent(){
	}
	
	
	public Map<KeyCode,KeyExpression> getMap(){
		return inputMap;
	}
	
	public void setMap(Map<KeyCode,KeyExpression> map){
		inputMap=map;
	}
	
	public void addToMap(KeyCode kc, KeyExpression ke){
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
		System.out.println("FUCK YOU KOPEN \t"+inputMap);
		return new KeyInputComponent(inputMap);
	}

}
