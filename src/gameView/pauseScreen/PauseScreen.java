package gameView.pauseScreen;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import gameView.AbstractViewer;
import gameView.UIView;
import gameView.commands.CloseCommand;
import gameView.userInput.IUserInputData;

public class PauseScreen extends AbstractViewer {
	
	private static final String myName = "PauseScreen";

	private Scene myScene;
	private VBox myBox;
	private Slider mySlider;
	
	public PauseScreen(UIView view, Stage s, IUserInputData userData) {
		super(view, s, userData);
		setStageAction();
		makeScene();
	}

	@Override
	public Scene getScene() {
		return myScene;
	}

	private void makeScene() {
		mySlider = new Slider(0, 1, 1);
		setSliderAction();
		myBox = new VBox(10, mySlider, makeButton(new CloseCommand(this)));
		myBox.setAlignment(Pos.CENTER);
		myScene = new Scene(myBox, UIView.DEFAULT_SIZE.width/2, UIView.DEFAULT_SIZE.height/5);
	}
	
	private void setSliderAction() {
	    mySlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    getUserInput().setRewind(new_val.doubleValue());
            }
        });
	}
	private void setStageAction() {
		
	}
}
