package components.hudComponents;

import components.IHUDComponent;

public class LivesComponent implements IHUDComponent {
	
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
	public HUDComponentType getComponentType() {
		return HUDComponentType.Lives;
	}

	@Override
	public IHUDComponent newCopy() {
		return new LivesComponent(getLives());
	}
}
