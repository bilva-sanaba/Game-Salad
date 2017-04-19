package view.toolbar;

import controller.Controller;
import javafx.stage.Stage;
import view.ViewData;

public class BackEvent implements ToolBarButtonEvent{
	
	private ViewData myData;
	
	public BackEvent(ViewData dataIn){
		myData = dataIn;
	}

	@Override
	public void event() {
		new Controller(new Stage());
	}

}
