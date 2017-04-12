package view.editor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import components.IComponent;
import components.entityComponents.MonsterMovementPattern;
import engines.IKeyInputPanel;
import engines.KeyInputPanel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import view.UtilityFactory;

public class MonsterEditor  extends ComponentEditor {
	private static final String ComponentName = "MonsterActions";
	private MonsterMovementPattern myMMP;
	private ArrayList<Node> nodeList = new ArrayList<Node>();
		
		private HBox myBox;
		
		public MonsterEditor(UtilityFactory utilf) {
			myBox = new HBox();
			final ToggleGroup group = addRadioButtons(nodeList);
			myBox.getChildren().addAll(nodeList);
			setInputNode(myBox);
		}
		private ToggleGroup addRadioButtons(List<Node> nodeList) {
			ToggleGroup group = new ToggleGroup();

			RadioButton rb1 = new RadioButton("RL");
			rb1.setToggleGroup(group);
			nodeList.add(rb1);

			RadioButton rb2 = new RadioButton("smart RL");
			rb2.setToggleGroup(group);
			rb2.setSelected(true);
			nodeList.add(rb2);
			
			RadioButton rb3 = new RadioButton("UD");
			rb3.setToggleGroup(group);
			nodeList.add(rb3);
			
			RadioButton rb4 = new RadioButton("smart UD");
			rb4.setToggleGroup(group);
			nodeList.add(rb4);

			
			group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
				public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
					if (new_toggle.equals(rb1)) {
						myMMP = MonsterMovementPattern.LeftAndRight;
					}else if (new_toggle.equals(rb2)) {
						myMMP = MonsterMovementPattern.LeftAndRightUntilCollision;
					}else if (new_toggle.equals(rb3)) {
						myMMP = MonsterMovementPattern.UpAndDown;
					}else{
						myMMP = MonsterMovementPattern.UpAndDownUntilCollision;
					}
				}
			});

			return group;
		}
		@Override
		public IComponent getComponent() {
			return getCompF().getComponent(ComponentName, myMMP);
		}
		
}
