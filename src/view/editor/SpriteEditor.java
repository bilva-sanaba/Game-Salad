package view.editor;

import components.IComponent;
import javafx.scene.Node;
import view.ImageChooser;
import view.UtilityFactory;

public class SpriteEditor extends ComponentEditor{
	private EditableComponents componentName = EditableComponents.Sprite;
	private ImageChooser imageChooser = new ImageChooser();
	private String myFilePath;

	
	public SpriteEditor(UtilityFactory utilf) {
		Node imageButton = utilf.buildButton("ChooseImageLabel", e -> {
			myFilePath = imageChooser.chooseFile();
		});
		setInputNode(imageButton);
	}
	
	@Override
	public IComponent getComponent() {
		return getCompF().getComponent(componentName.toString(), myFilePath);
	}

}
