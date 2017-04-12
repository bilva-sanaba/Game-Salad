package view.editor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import components.IComponent;
import engines.IKeyInputPanel;
import engines.KeyInputPanel;
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
	private static final String ComponentName = "KeyInput";
	private static final String KEYINPUT = "KeyInput : ";
	private Map<KeyCode,IKeyExpression> inputMap = new HashMap<KeyCode,IKeyExpression>();
		
		private HBox myBox;
		private Text myLabel = new Text(KEYINPUT);

		
		public KeyInputEditor(UtilityFactory utilf) {
			System.out.println("kill yourself");
			myBox = new HBox();
			
			System.out.println("kill yourself now asshole");
			myBox.getChildren().add(myLabel);
			addRadioButtons(myBox.getChildren());
                                
			System.out.println(myBox);
			setInputNode(myBox);
		}
		
		@Override
		public IComponent getComponent() {
			return getCompF().getComponent(ComponentName, inputMap);
		}
		
		
		private ToggleGroup addRadioButtons(List<Node> nodeList) {
			ToggleGroup group = new ToggleGroup();

			RadioButton rb1 = new RadioButton("Hero");
			rb1.setToggleGroup(group);
			GridPane.setConstraints(rb1, 0, 3);
			nodeList.add(rb1);

			RadioButton rb2 = new RadioButton("Monster");
			rb2.setToggleGroup(group);
			rb2.setSelected(true);
			GridPane.setConstraints(rb2, 1, 3);
			nodeList.add(rb2);

			group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
				public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
					if (new_toggle.equals(rb1)) {
						IKeyInputPanel kip = new KeyInputPanel();
						kip.openWindow();
						inputMap = kip.getMap();
					} else{
						inputMap.clear();
					}
				}
			});

			return group;
		}

}
