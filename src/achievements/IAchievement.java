package achievements;

import javafx.scene.Group;
import javafx.scene.shape.Ellipse;
/**
 * 
 * @author Jacob
 *
 */
public interface IAchievement {

	public Group getGroup();
	
	public void updateAchievementLoc(double d);
}
