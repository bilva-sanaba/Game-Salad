package engines;

import java.util.Map;

import components.entityComponents.KeyExpression;
import javafx.scene.input.KeyCode;

public interface IKeyInputPanel {
	public Map<KeyCode,KeyExpression>getMap();
}
