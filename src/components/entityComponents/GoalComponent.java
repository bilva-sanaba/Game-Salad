package components.entityComponents;

import components.IComponent;

public class GoalComponent implements IComponent {
	
	private boolean goalSatisfied = false;
	
	public GoalComponent(boolean g){
		System.out.println(g + "make goal");
		goalSatisfied = g;
	}
	
	public GoalComponent(){
		super();
	}
	
	@Override
	public ComponentType getComponentType() {
		return ComponentType.Goal;
	}

	@Override
	public IComponent newCopy() {
		return new GoalComponent(goalSatisfied);
		
	}
	
	public void satisfyGoal(boolean bool){
		goalSatisfied = bool;
	}
	
	public boolean checkIfSatisfied(){
		return goalSatisfied;
	}
	
}
