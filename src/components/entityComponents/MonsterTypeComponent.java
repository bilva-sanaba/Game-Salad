package components.entityComponents;

import components.AComponent;
import components.IComponent;

public class MonsterTypeComponent extends AComponent implements IComponent{
	
	private MonsterType typeOfMonster;

	public MonsterTypeComponent(MonsterType type) {
	System.out.println("made " + type);
		typeOfMonster = type;
	}
	
	public MonsterTypeComponent(String s){
		typeOfMonster = MonsterType.valueOf(s);
	}
	
	public String getTypeString() {
		return typeOfMonster.name();
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.MonsterType;
		
	}
	
	public MonsterType getType() {
		return typeOfMonster;
	}

	@Override
	public IComponent newCopy() {
		return new MonsterTypeComponent(getType());
	}

	
	
}
