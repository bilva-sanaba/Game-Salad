package view.editor;

import components.IComponent;
import entity.Entity;
import javafx.scene.layout.HBox;
import view.UtilityFactory;
import view.window.EntityMakerWindow;

public class ObjectCreationEditor extends ComponentEditor{
	private EditableComponents componentName = EditableComponents.ObjectCreation;
	private HBox myBox;
	private Entity myEntity;
	private UtilityFactory myUtil;

	public ObjectCreationEditor(UtilityFactory utilf) {
		System.out.println("made?");
		myBox = new HBox();
		myUtil = utilf;
		myBox.getChildren().add(utilf.buildButton("AddObjectCreation", e -> openCopnentWindow()));		
		setInputNode(myBox);
	}
	
	private void openCopnentWindow() {
		EntityMakerWindow emw = new EntityMakerWindow(myUtil);
		emw.showEntityBuilder();;
		myEntity = emw.getEntity();
	}

	@Override
	public IComponent getComponent() {
		System.out.println(componentName);
		return getCompF().getComponent(componentName.toString(), myEntity);
	}
}
