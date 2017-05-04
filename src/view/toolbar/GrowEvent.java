package view.toolbar;

import view.UtilityFactory;
import view.ViewData;

public class GrowEvent implements ToolBarButtonEvent {

	ViewData myData;

	public GrowEvent(UtilityFactory utilF, ViewData data) {
		myData = data;
	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		
	}
}
