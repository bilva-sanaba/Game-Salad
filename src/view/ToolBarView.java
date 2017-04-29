package view;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Region;

public class ToolBarView extends GUIComponent {
	private ToolBar myBar;
	private ViewData myData;

	public ToolBarView(UtilityFactory utilF, ViewData data) {
		myData = data;
		myBar = new ToolBar();
		fillBar(utilF.makeToolBarButtons(myData));
	}

	private void fillBar(List<Button> toolBarButtons) {
		toolBarButtons.stream().forEach(myBar.getItems()::add);
	}

	@Override
	public Region buildComponent() {
		return myBar;
	}
}
