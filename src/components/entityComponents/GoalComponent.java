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
		return new GoalComponent();
	}
	
	public void satisfyGoal(boolean bool){
		goalSatisfied = bool;
	}
	
	public boolean checkIfSatisfied(){
		return goalSatisfied;
	}
	
}
