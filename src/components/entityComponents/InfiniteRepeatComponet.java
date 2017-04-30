package components.entityComponents;

import components.AComponent;
import components.IComponent;

public class InfiniteRepeatComponet extends AComponent implements IComponent {

	@Override
	public ComponentType getComponentType() {
		return ComponentType.InfiniteRepeat;
	}

	private boolean repeat;

	public InfiniteRepeatComponet(boolean r) {
		repeat = r;
	}
	
	public InfiniteRepeatComponet(){
		super();
	}

	public boolean getRepeat() {
		return repeat;
	}

	public void setRepeat(boolean r) {
		repeat = r;
	}

	public IComponent newCopy() {
		return new CollidableComponent(getRepeat());
	}

}
