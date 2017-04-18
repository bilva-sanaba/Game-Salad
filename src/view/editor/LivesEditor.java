package view.editor;

import components.IComponent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.HBox;
import view.UtilityFactory;

public class LivesEditor extends ComponentEditor {
	private static final String ComponentName = "Lives";
	
	private HBox myBox;
	private int myLives;
	
	public LivesEditor(UtilityFactory utilf) {
		myBox = utilf.buildSlider(ComponentName, new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myLives = newValue.intValue(); 
				System.out.println(myLives);
			}
		});
		setInputNode(myBox);
	}
	
	public IComponent getComponent() {
		return getCompF().getComponent(ComponentName, myLives);
	}
	
}