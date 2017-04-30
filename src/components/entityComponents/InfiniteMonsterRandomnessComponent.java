package components.entityComponents;

import components.AComponent;
import components.IComponent;

public class InfiniteMonsterRandomnessComponent extends AComponent implements IComponent {
	private double monsterRandomness;

	@Override
	public ComponentType getComponentType() {
		return ComponentType.InfiniteMonsterRandomness;
	}


	public InfiniteMonsterRandomnessComponent(double rm) {
		monsterRandomness = rm;
	}
	
	public InfiniteMonsterRandomnessComponent(){
		super();
	}

	public double getMonsterRandomness() {
		return monsterRandomness;
	}

	public void setMonsterRandomness(double rm) {
		monsterRandomness = rm;
	}

	public IComponent newCopy() {
		return new InfiniteMonsterRandomnessComponent(getMonsterRandomness());
	}

}
