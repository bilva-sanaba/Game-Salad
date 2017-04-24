package components.entityComponents;

import components.IComponent;

public class FrontCameraComponent implements IComponent{
	
	private boolean scrolling = false;

	@Override
	public ComponentType getComponentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IComponent newCopy() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setScrolling(boolean shouldScroll){
		scrolling = shouldScroll;
	}

}
