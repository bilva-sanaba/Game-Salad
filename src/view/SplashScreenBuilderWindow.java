package view;

import entity.Entity;
import entity.SplashEntity;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class SplashScreenBuilderWindow {

	public SplashScreenBuilderWindow(){
		
	}
	
	public void openWindow(){
		Stage stage = new Stage();
		GridPane root = new GridPane();
		root.setPadding(new Insets(10));
		root.setHgap(20);
		root.setVgap(20);
//		pickColor(root);
		selectText(root);
		Button okayButton = new Button("OkayButtonLabel");
		okayButton.setOnAction(e -> {
			//TODO: pass the color to a new entity or something?
			//Entity myEntity = new Entity();
			//myEntity.addComponent();
			//myData.addToConfigurationEntityList(myEntity);
			stage.close();
		});
		root.getChildren().add(okayButton);
		GridPane.setConstraints(okayButton, 0, 5);
		GridPane.setColumnSpan(okayButton, 2);
		Scene scene = new Scene(root, 350, 300);
		stage.setScene(scene);
		stage.setTitle("Customize Splash Screen");
		stage.show();
		
		
//		beneath here is a splash entity which you instantiate with all the values you just found at the x's
//		SplashEntity s = new SplashEntity(1, x,x,x);
//		return s;
	}
	
//	private void pickColor(Pane root){
//		Label backgroundColorTitle = new Label("Background Color");
//		GridPane.setConstraints(backgroundColorTitle, 0, 0);
//		ColorPicker colorPicker = new ColorPicker();
//		GridPane.setConstraints(colorPicker, 1, 1);
//		Circle circle = new Circle(50);
//		GridPane.setConstraints(circle, 0, 1);
//		circle.setFill(colorPicker.getValue());
//		colorPicker.setOnAction(e -> circle.setFill(colorPicker.getValue()));
//		root.getChildren().addAll(backgroundColorTitle, circle, colorPicker);	
//	}
	
	private void selectText(Pane root){
		Label title = new Label("Game Title:");
		GridPane.setConstraints(title, 0, 2);
		TextField input = new TextField ();
		GridPane.setConstraints(input, 1, 2);
		root.getChildren().addAll(title, input);
	}
}
