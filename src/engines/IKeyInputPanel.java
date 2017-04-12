package engines;

import java.util.Map;

import components.KeyExpression;
import javafx.scene.input.KeyCode;

public interface IKeyInputPanel {
	public Map<KeyCode,KeyExpression>getMap();
	public void openWindow();
}