package gameView.pauseScreen;


import javafx.scene.control.Label;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import gameView.AbstractViewer;
import gameView.UIView;
import gameView.UIViewInterface;
import gameView.commands.CloseCommand;
import gameView.tools.ResourceRetriever;
import gameView.userInput.IUserInputData;

public class PauseScreen extends AbstractViewer {
	
	private static final String myName = "PauseScreen";

	private Scene myScene;
	private VBox myBox;
	private Slider mySlider;
	
	public PauseScreen(UIViewInterface view, Stage s, IUserInputData userData) {
		super(view, s, userData);
		setStageAction();
		makeScene();
	}

	@Override
	public Scene getScene() {  
		return myScene;
	}

	private void makeScene() {
		myBox = new VBox(20, sliderBox(), makeButton(new CloseCommand(this)));
		myBox.setAlignment(Pos.CENTER);
		myScene = new Scene(myBox, UIView.DEFAULT_SIZE.width/3, UIView.DEFAULT_SIZE.height/5);
		myScene.getStylesheets().add(new ResourceRetriever().getStyleSheets(this,myName));
	}
	
	private void setSliderAction() {
	    mySlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,  
                Number old_val, Number new_val) {
                    getUserInput().setRewind(new_val.doubleValue());
            }  
        });
	}
	
	private HBox sliderBox() {
		Label lab = makeLabel("Rewind:", "rewind");
		mySlider = new Slider(0, 1, 1);
		mySlider.setPrefWidth(UIView.DEFAULT_SIZE.width/4);
		setSliderAction();
		HBox newBox = new HBox(10, lab, mySlider);
		newBox.setAlignment(Pos.CENTER);
		return newBox;
		
		
	}
	private void setStageAction() {
		
	}

	@Override
	protected void setBackground(String s) {
	}

	@Override
	protected Pane getButtonContainer() {
		return myBox;
	}
}
