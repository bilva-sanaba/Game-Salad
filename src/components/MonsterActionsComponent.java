package components;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.input.KeyCode;

public class MonsterActionsComponent implements IComponent {
	private Map<KeyCode,KeyExpression> actionMap = new HashMap<KeyCode,KeyExpression>();
	
	public MonsterActionsComponent(Map<KeyCode, KeyExpression> keyMap){
		actionMap=keyMap;
	}
	
	public MonsterActionsComponent(){
	}
	
	
	public Map<KeyCode,KeyExpression> getMap(){
		return actionMap;
	}
	
	public void setMap(Map<KeyCode,KeyExpression> map){
		actionMap=map;
	}
	
	public void addToMap(KeyCode kc, KeyExpression ke){
		actionMap.put(kc, ke);
	}
	
	public void removeFromMap(KeyCode kc){
		actionMap.remove(kc);
	}
	
	@Override
	public ComponentType getComponentType() {
		return ComponentType.MonsterAction;
	}

	@Override
	public IComponent newCopy() {
		return new MonsterActionsComponent(actionMap);
	}
}

