package view.editor;

import java.util.ArrayList;
import java.util.Map;

import actions.IAction;
import components.entityComponents.IKeyExpression;
import javafx.scene.input.KeyCode;

public interface IKeyInputPanel {

	public Map<KeyCode, ArrayList<IAction>> getMap();
	public void openWindow();
}

