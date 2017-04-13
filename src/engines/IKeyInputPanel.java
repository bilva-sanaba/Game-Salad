package engines;

import java.util.Map;

import components.entityComponents.IKeyExpression;
import javafx.scene.input.KeyCode;

public interface IKeyInputPanel {

	public Map<KeyCode,IKeyExpression>getMap();
	public void openWindow();
}

