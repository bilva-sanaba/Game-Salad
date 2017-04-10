package view;

import javafx.scene.Node;

public abstract class ComponentEditor {
	private Node myInputNode;
	
	protected void setInputNode(Node newNode){
		myInputNode = newNode;
		System.out.println(myInputNode);
	}
	
	public Node getInputNode() {
		return myInputNode;
	}
}
