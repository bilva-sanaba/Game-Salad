package achievements;

import javafx.animation.AnimationTimer;

public class AchievementTimer extends AnimationTimer {

	@Override
	public void handle(long now) {
		// TODO Auto-generated method stub
		if(now%30==0){
			this.stop();
		}
	}

}
