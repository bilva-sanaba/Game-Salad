package components.entityComponents;

import components.AComponent;
import components.IComponent;

public class InfiniteBlockPatternComponent extends AComponent implements IComponent {
	private BlockPattern myBP;
	
	public InfiniteBlockPatternComponent(BlockPattern bp){
		myBP=bp;
	}
	
	public InfiniteBlockPatternComponent(String s) {
		for (BlockPattern bp: BlockPattern.values()) {
			if (s.equals(bp.toString())) {
				myBP = bp;
			}
		}
	}
	
	public InfiniteBlockPatternComponent(){
	}
	
	
	public BlockPattern getOrientation(){
		return myBP;
	}
	
	public void setOrientation(BlockPattern bp){
		myBP = bp;
	}
	@Override
	public ComponentType getComponentType() {
		return ComponentType.InfiniteBlockPattern;
	}

	@Override
	public IComponent newCopy() {
		return new InfiniteBlockPatternComponent(myBP);
	}

}
