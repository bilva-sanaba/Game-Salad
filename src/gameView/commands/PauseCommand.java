package gameView.commands;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Stage;
import controller.VoogaAlert;
import gameView.ICommandView;
import gameView.UIView;

public class PauseCommand extends AbstractCommand {

	public PauseCommand(ICommandView m) {
		super(m);
	}

	@Override
	public void execute(Stage s) {
		((ICommandView) getView()).pauseGame();
//		 ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);
//		 GaussianBlur blur = new GaussianBlur(55); // 55 is just to show edge effect more clearly.
//		 adj.setInput(blur);
//		 s.getScene().getRoot().setEffect(adj);
//		 Stage newStage = createStage();
//		 newStage.showAndWait();
//		 adj = null;
//		 s.getScene().getRoot().getEffect(adj);
	}

	@Override
	public String getName() {
		return "Pause";
	}

	private Stage createStage() {
		Stage newStage = new Stage();
		Group root = new Group();
		root.getChildren().add(makeButton(newStage));
		Scene newScene = new Scene(root, UIView.DEFAULT_SIZE.width/10, UIView.DEFAULT_SIZE.height/10);
		return newStage;
	}
	
	private Button makeButton(Stage s) {
		Button playButton = new Button("Continue");
		playButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				s.close();
			}
		});
		return playButton;
	}
}
