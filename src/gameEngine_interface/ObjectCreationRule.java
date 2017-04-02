package gameEngine_interface;

import java.util.Collection;

public interface ObjectCreationRule {
	public Entity createSprite(Collection<Object> interactingObjects);
}
