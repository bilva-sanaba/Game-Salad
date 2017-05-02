package gameView.tools;

import java.awt.Dimension;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

public class ScrollablePopup {
	
	private Scene myScene;
	private ScrollPane myScroll;
	private BorderPane myPane;
	
	public ScrollablePopup(String name, String cssFile, Node contentToScroll, Button button, Dimension dim) {
		setUpScroll(contentToScroll);
		setUpBorderPane(button);
		myScene = new Scene(myPane, dim.width, dim.height);
		myScene.getStylesheets().add(cssFile);//this.getClass().getResource(cssFile).toExternalForm());
	}
	
	/**
	 * Get the scene associated with the scrollable
	 * @return
	 */
	public Scene getScene() {
		return myScene;
	}
	
	private void setUpScroll(Node content) {
		myScroll = new ScrollPane();
		myScroll.setId("scroll");
		myScroll.setContent(content);
		myScroll.setFitToWidth(true);
	}
	
	private void setUpBorderPane(Button button) {
		myPane = new BorderPane();
		myPane.setId("pane");
		myPane.setCenter(myScroll);
		if (button != null) {
			BorderPane.setAlignment(button, Pos.CENTER);
			BorderPane.setMargin(button, new Insets(10));
			myPane.setBottom(button);
		}
	}

}
