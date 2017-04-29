package components.entityComponents;

import components.IComponent;

public class MonsterMovementStepsComponent implements IComponent {
	
	private int steps = 0;
	private int stepsLeft = 0;
	
	public MonsterMovementStepsComponent(int stepsInPattern){
		stepsLeft = stepsInPattern;
		steps = stepsInPattern;
	}
	
	public void update(){
		if(stepsLeft <= 0){
			stepsLeft = steps;
		}
		else{
			stepsLeft--;
		}
		
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.MonsterMovementStep;
	}

	@Override
	public IComponent newCopy() {
		// TODO Auto-generated method stub
		return new MonsterMovementStepsComponent(steps);
	}

}
