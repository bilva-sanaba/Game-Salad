package gameView.commands;

import java.awt.Dimension;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import gameView.ICommandGameView;
import gameView.ICommandView;
import gameView.UIView;
import gameView.tools.DisplayManager;
import gameView.tools.ScrollablePopup;

public class PreferencesCommand extends AbstractCommand {

	private Stage myStage;
	private Scene myScene;
	private VBox myBox;
	private DisplayManager myDisplays;
	private ScrollablePopup myPopup;
	
	public PreferencesCommand(ICommandView m) {
		super(m);
		myBox = new VBox(); 
		myBox.setId("box");
		myStage = new Stage();
		myPopup = new ScrollablePopup("Preferences", "/resources/Preferences.css", myBox, makeCloseButton(),
				new Dimension(UIView.DEFAULT_SIZE.width/3, UIView.DEFAULT_SIZE.height/3));
		myDisplays = ((ICommandGameView) getView()).getComponents();
	}

	@Override
	public void execute(Stage s) {
		myBox.getChildren().clear();
		myStage.setTitle(getName());
		makeBox(); 
		makeCloseButton();
		myStage.setScene(myPopup.getScene());
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
		checkbox.setSelected(myDisplays.checkIfActive(name));
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
	
	private Button makeCloseButton() {
		Button close = new Button("Close");
		close.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				myStage.close();
			}
		});
		return close;
	}

}
