package engines;

import java.util.HashMap;
import java.util.Map;

import components.KeyExpression;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
public class SplashScreenBuilderWindow {

	public SplashScreenBuilderWindow(){
		
	}
	
	
	
	private void pickColor(Pane root){
		Label backgroundColorTitle = new Label("Background Color");
		ColorPicker colorPicker = new ColorPicker();
		Circle circle = new Circle(50);
		circle.setFill(colorPicker.getValue());
		colorPicker.setOnAction(e -> circle.setFill(colorPicker.getValue()));
		root.getChildren().addAll(backgroundColorTitle, circle, colorPicker);	
	}
	
	private void selectText(Pane root){
		Label title = new Label("Game Title:");
		TextField input = new TextField ();
		root.getChildren().addAll(title, input);
	}
}
public class KeyInputPanel implements IKeyInputPanel{
	private Map<KeyCode, KeyExpression> keyMap = new HashMap<KeyCode, KeyExpression>();
	public KeyInputPanel(){
		this.setX(300);
		this.setY(300);
		
	}
	private void addContent(){
		Button b = new Button();
	    b.setOnAction(e-> {getMap();this.hide();});
	    b.setText("lol");
		this.getContent().addAll(new Rectangle(25, 25, Color.BLACK),b);
	}
	@Override
	public Map<KeyCode, KeyExpression> getMap() {
		// TODO Auto-generated method stub
		return null;
	}
	public void openWindow(){
		Stage stage = new Stage();
		GridPane root = new GridPane();
		root.setPadding(new Insets(10));
		root.setHgap(20);
		root.setVgap(20);
		pickColor(root);
		selectText(root);
		Button okayButton = new Button("OkayButtonLabel");
		okayButton.setOnAction(e -> {
			//TODO: pass the color to a new entity or something?
			stage.close();
		});
		root.getChildren().add(okayButton);
		Scene scene = new Scene(root, 230, 400);
		stage.setScene(scene);
		stage.show();
	}
	
}
