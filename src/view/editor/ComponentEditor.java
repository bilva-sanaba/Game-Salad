package view.editor;

import javafx.scene.Node;

public abstract class ComponentEditor {
	private Node myInputNode;
	
	protected void setInputNode(Node newNode){
		myInputNode = newNode;
	}
	
	public Node getInputNode() {
		return myInputNode;
	}
}
