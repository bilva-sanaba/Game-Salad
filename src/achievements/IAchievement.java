package achievements;

import javafx.scene.Group;
import javafx.scene.shape.Ellipse;

public interface IAchievement {

	public Group getGroup();
	
	public void updateAchievementLoc(double d);
}