package gameEngine_interface;

import java.util.Collection;

public interface ObjectCreationRule {
	public Sprite createSprite(Collection<Object> interactingObjects);
}
