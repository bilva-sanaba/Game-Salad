package components.entityComponents;

import components.AComponent;
import components.IComponent;

public class ScoreComponent extends AComponent implements IComponent {

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
	public ComponentType getComponentType() {
		return ComponentType.Score;
	}

	@Override
	public IComponent newCopy() {
		return new ScoreComponent(getScore());
	}

}
