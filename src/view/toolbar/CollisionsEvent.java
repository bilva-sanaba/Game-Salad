package view.toolbar;

import view.UtilityFactory;
import view.ViewData;

public class CollisionsEvent implements ToolBarButtonEvent {
	ViewData myData;

	public CollisionsEvent(UtilityFactory utilF, ViewData data) {
		myData = data;
	}

	@Override
	public void event() {
		// TODO Auto-generated method stub

	}
}
