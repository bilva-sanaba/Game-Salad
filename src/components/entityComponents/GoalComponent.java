package components.entityComponents;

import components.IComponent;

public class GoalComponent implements IComponent {
	
	private boolean goalSatisfied = false;

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Goal;
	}

	@Override
	public IComponent newCopy() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void satisfyGoal(){
		goalSatisfied = true;
	}
	
	public boolean checkIfSatisfied(){
		return goalSatisfied;
	}
	
}
