package gameEngine_interface;

import java.util.Collection;
import java.util.List;

public interface Rule {
	/**
	 * Returns the effect
	 * @param interactingObjects
	 */
	public Rule getUpdatedRule(Collection<Object> interactingObjects);
	public List<Object> checkNeededObjects();
}
