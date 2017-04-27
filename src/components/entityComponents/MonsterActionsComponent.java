package components.entityComponents;

import java.util.HashMap;
import java.util.Map;

import components.AComponent;
import components.IComponent;
import javafx.scene.input.KeyCode;

public class MonsterActionsComponent extends AComponent implements IComponent {
	private MonsterMovementPattern myMMP;
	
	public MonsterActionsComponent(MonsterMovementPattern mmp){
		myMMP=mmp;
	}
	
	public MonsterActionsComponent(String s) {
		for (MonsterMovementPattern p: MonsterMovementPattern.values()) {
			if (s.equals(p.toString())) {
				myMMP = p;
			}
		}
	}
	
	public MonsterActionsComponent(){
	}
	
	
	public MonsterMovementPattern getMMP(){
		return myMMP;
	}
	
	public void setMMP(MonsterMovementPattern mmp){
		myMMP = mmp;
	}
	
	@Override
	public ComponentType getComponentType() {
		return ComponentType.MonsterAction;
	}

	@Override
	public IComponent newCopy() {
		return new MonsterActionsComponent(getMMP());
	}
}

