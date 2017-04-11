package view.editor;

import components.IComponent;
import javafx.scene.Node;
import view.ComponentFactory;

public abstract class ComponentEditor {
	private Node myInputNode;
	private newComponentFactory compF= new ComponentFactory();
	
	protected void setInputNode(Node newNode){
		myInputNode = newNode;
	}
	
	protected newComponentFactory getCompF(){
		return compF;
	}
	
	public Node getInputNode() {
		return myInputNode;
	}
	
	public abstract IComponent getComponent();
}
