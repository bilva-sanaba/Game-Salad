package view.editor;

import components.IComponent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import view.UtilityFactory;

public class StationaryEditor extends ComponentEditor{
	private EditableComponents componentName = EditableComponents.Stationary;
	private String[] station = {"false"}; // Initialize array
	private HBox myBox;
	private boolean myStation;


	public StationaryEditor(UtilityFactory utilf) {
		myBox = new HBox();
		final ToggleGroup group = utilf.buildRadioButtonGroup("SelectStationaryType", myBox);
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				station = (String[]) new_toggle.getUserData();
				myStation = station[0].equals("true");
			}
		});			
		setInputNode(myBox);
	}
	
	@Override
	public IComponent getComponent() {
		return getCompF().getComponent(componentName.toString(), myStation);
	}
}
