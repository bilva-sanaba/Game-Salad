package view.editor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import actions.IAction;
import components.IComponent;
import components.entityComponents.IKeyExpression;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import view.UtilityFactory;

public class KeyInputEditor extends ComponentEditor {
	private EditableComponents componentName = EditableComponents.KeyInput;
	private String[] myKeyString = {"false"}; // Initialize array
	private Map<KeyCode,ArrayList<IAction>> inputMap = new HashMap<KeyCode,ArrayList<IAction>>();

		private HBox myBox;
		
		public KeyInputEditor(UtilityFactory utilf) {
			myBox = new HBox();
			final ToggleGroup group = utilf.buildRadioButtonGroup("SelectCharacterType", myBox);
			group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
				public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
					myKeyString = (String[]) new_toggle.getUserData();
					if (myKeyString[0].equals("true")){ // boolean if true
						IKeyInputPanel kip = new KeyInputPanel();
						kip.openWindow();
						inputMap = kip.getMap();
					}
				}
			});			
			setInputNode(myBox);
		}
		
		@Override
		public IComponent getComponent() {
			return getCompF().getComponent(componentName.toString(), inputMap);
		}
}