package components.entityComponents;

import components.AComponent;
import components.IComponent;

public class LivesComponent extends AComponent implements IComponent {
	
	private int lives;
	
	public LivesComponent(){
		lives=3;
	}
	
	public LivesComponent(int liv){
		lives = liv;
	}

	public void setLives(int setLives){
		lives=setLives;
	}
	public int getLives(){
		return lives;
	}
	@Override
	public ComponentType getComponentType() {
		return ComponentType.Lives;
	}

	@Override
	public IComponent newCopy() {
		return new LivesComponent(getLives());
	}
}
