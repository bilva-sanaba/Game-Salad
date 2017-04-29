package components.entityComponents;

import components.IComponent;

public class BackCameraComponent implements IComponent{

	private boolean scrolling = false;
	
	@Override
	public ComponentType getComponentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IComponent newCopy() {
		// TODO Auto-generated method stub
		return new BackCameraComponent();
	}
	
	public void setScrolling(boolean shouldScroll){
		scrolling = shouldScroll;
	}
	
	public boolean getScrolling(){
		return scrolling;
	}

}
