package gameView.commands;

import java.util.Collection;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import gameView.ICommandGameView;
import gameView.ICommandView;
import gameView.UIDisplayComponent;
import gameView.UIView;
import gameView.tools.DisplayManager;

public class PreferencesCommand extends AbstractCommand {

	private Stage myStage;
	private Scene myScene;
	private VBox myBox;
	private DisplayManager myDisplays;
	
	public PreferencesCommand(ICommandView m) {
		super(m);
		myBox = new VBox();
		myBox.setId("pane");
		myDisplays = ((ICommandGameView) getView()).getComponents();
		myScene = new Scene(myBox, UIView.DEFAULT_SIZE.width/4, UIView.DEFAULT_SIZE.height/4);
	}

	@Override
	public void execute(Stage s) {
		myStage = new Stage();
		makeBox();
		makeCloseButton();
		myStage.setScene(myScene);
		myStage.show();
		
	}

	@Override
	public String getName() {
		return "Preferences";
	}
	
	private void makeBox() {
		myDisplays.getNames().stream()
			.forEach(c-> {
				myBox.getChildren().add(makeCheckBox(c, myDisplays.checkIfActive(c)));
			});
	}
	
	private CheckBox makeCheckBox(String name, boolean bool) {
		CheckBox checkbox = new CheckBox(name);
		checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        public void changed(ObservableValue<? extends Boolean> ov,
	            Boolean old_val, Boolean new_val) {
	                if (new_val) {
	                	myDisplays.add(name);
	                } else {
	                	myDisplays.remove(name);
	                }
	        }
	    });
		return checkbox;
	}
	
	private void makeCloseButton() {
		Button close = new Button("Close");
		close.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				myStage.close();
			}
		});
	}

}
