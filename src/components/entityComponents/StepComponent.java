package components.entityComponents;

import components.AComponent;
import components.IComponent;

public class StepComponent extends AComponent implements IComponent {
	private int steps;
	private int stepsLeft;

	public StepComponent(int s) {
		steps = s;
		stepsLeft = s;
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
	
	public void takeStep(){
		steps--;
	}

	public IComponent newCopy() {
		return new StepComponent(getTotalStep());
	}
}