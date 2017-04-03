package gameEngine_interface;

import java.util.List;
/**
 * This class will be used by the GameEngine to change Sprites based on an appropriate Rule
 * @author Bilva
 *
 */
public interface RuleHandler {
	
	/**
	 * Given a rule, changes Sprites accordingly
	 * @param rule
	 * @param allSprites
	 */
	public void handleRule (Rule rule, List<Sprite> allSprites);
}
