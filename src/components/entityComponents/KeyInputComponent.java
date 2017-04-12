package components.entityComponents;

import java.util.HashMap;
import java.util.Map;

import components.IComponent;
import javafx.scene.input.KeyCode;

public class KeyInputComponent implements IComponent {
	private Map<KeyCode,IKeyExpression> inputMap = new HashMap<KeyCode,IKeyExpression>();
	
	public KeyInputComponent(Map<KeyCode,IKeyExpression> keyMap){
		inputMap=keyMap;
	}
	
	public KeyInputComponent(){
	}
	
	
	public Map<KeyCode,IKeyExpression> getMap(){
		return inputMap;
	}
	
	public void setMap(Map<KeyCode,IKeyExpression> map){
		inputMap=map;
	}
	
	public void addToMap(KeyCode kc, IKeyExpression ke){
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
