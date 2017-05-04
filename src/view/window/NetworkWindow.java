package view.window;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.GUIBuilder;
import view.UtilityFactory;
import view.ViewData;

public class NetworkWindow implements Window {
	private Stage myStage;
	private ViewData myData;
	private UtilityFactory myUtilF;
	
	// TODO: make resource file with button names, cuz utilFactory buildButton uses resource files
	
	public NetworkWindow(UtilityFactory utilF, ViewData data) {
		myUtilF = utilF;
		myData = data;
	}
	
	private VBox makeRoot() {
		VBox root = new VBox();
		root.getChildren().addAll(makeJoinRoomGUI(), makeNewRoomGUI());
		return root;
	}
	
	private VBox makeJoinRoomGUI() {
		VBox container = new VBox();
		container.getStyleClass().add("narrow-box");
		
		Label title = new Label("Join Room:");
		
		HBox ipBox = new HBox();
		HBox portBox = new HBox();
		Label ipLabel = new Label("IP Address: ");
		Label portLabel = new Label("Port No: ");
		TextField ipInput = new TextField();
		TextField portInput = new TextField();
		ipBox.getChildren().addAll(ipLabel, ipInput);
		portBox.getChildren().addAll(portLabel, portInput);
		
		Button joinRoomButton = makeJoinRoomButton();
		
		container.getChildren().addAll(title, ipBox, portBox, joinRoomButton);
		
		return container;
	}
	
	private Button makeJoinRoomButton() {
		Button joinRoomButton = myUtilF.buildButton("Join", e -> {
			
		});
		
		return joinRoomButton;
	}
	
	private VBox makeNewRoomGUI() {
		VBox container = new VBox();
		container.getStyleClass().add("narrow-box");
		
		HBox buttonBox = new HBox();
		buttonBox.getStyleClass().add("narrow-box");
		Button newRoomButton = makeNewRoomButton();
		Button closeRoomButton = makeCloseRoomButton();
		buttonBox.getChildren().addAll(newRoomButton, closeRoomButton);
		
		HBox ipBox = new HBox();
		HBox portBox = new HBox();
		Label yourIP = new Label("Your IP is: ");
		Label yourPort = new Label("Your port is: ");
		Text ipDisplay = new Text("");
		Text portDisplay = new Text("");
		ipBox.getChildren().addAll(yourIP, ipDisplay);
		portBox.getChildren().addAll(yourPort, portDisplay);
		
		container.getChildren().addAll(buttonBox, ipBox, portBox);
		
		return container;
	}
	
	private Button makeNewRoomButton() {
		Button newRoomButton = myUtilF.buildButton("New Room", e -> {
			
		});
		
		return newRoomButton;
	}
	
	private Button makeCloseRoomButton() {
		Button closeRoomButton = myUtilF.buildButton("Close Room", e -> {
			
		});
		
		return closeRoomButton;
	}
	
	private Scene buildScene() {
		VBox root = makeRoot();
		Scene myScene = new Scene(root, 350, 400);
		myScene.getStylesheets().add(GUIBuilder.RESOURCE_PACKAGE + GUIBuilder.STYLESHEET);
		root.getStyleClass().add("wide-box");
		return myScene;
	}
	
	@Override
	public void openWindow() {
		myStage = new Stage();
		myStage.setScene(buildScene());
		myStage.show();
	}
}
