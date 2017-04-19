package components.entityComponents;

import components.IComponent;

public class StepComponent implements IComponent {
	private int steps;
	private int stepsLeft;

	public StepComponent(int s) {
		steps = s;
	}
	
	public StepComponent(int s, int sl) {
		steps = s;
		stepsLeft = sl;
	}
	
	public StepComponent(){
		super();
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Step;
	}

	public int getTotalStep() {
		return steps;
	}
	
	public int getStepLeft() {
		return stepsLeft;
	}

	public void setStep(int s) {
		steps = s;
	}

	public IComponent newCopy() {
		return new StepComponent(getTotalStep());
	}
}