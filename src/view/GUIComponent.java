package view;

import java.util.ResourceBundle;

import javafx.scene.layout.Region;

public abstract class GUIComponent {
	public ResourceBundle resources = ResourceBundle.getBundle("resources/english");
	public UtilityFactory util = new UtilityFactory();
	public abstract Region buildComponent();
}