package gameEngine_interface;

import java.util.Collection;
import java.util.List;

public interface Component {
	/**
	 * Returns the effect
	 * @param interactingObjects
	 */
	public Component getUpdatedRule(Collection<Object> interactingObjects);
	public List<Object> checkNeededObjects();
	
	
	
	public void runRule();
}
