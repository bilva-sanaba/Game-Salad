package components.hudComponents;

import components.IHUDComponent;

public class ScoreComponent implements IHUDComponent {

	private int score;
	
	public ScoreComponent(){
		score=0;
	}
	
	public ScoreComponent(int sc){
		score=sc;
	}

	public void addScore(){
		score++;
	}
	
	public int getScore(){
		return score;
	}
	@Override
	public HUDComponentType getComponentType() {
		return HUDComponentType.Score;
	}

	@Override
	public IHUDComponent newCopy() {
		return new ScoreComponent(getScore());
	}

}
