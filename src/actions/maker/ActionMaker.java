package actions.maker;

import actions.IAction;
import javafx.scene.Node;

public abstract class ActionMaker {
	private Node myInputNode;
	
	protected void setInputNode(Node newNode){
		myInputNode = newNode;
	}
	
	public Node getInputNode() {
		return myInputNode;
	}
	
	public abstract IAction getComponent();

}
