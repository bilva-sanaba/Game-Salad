package view.toolbar;

import view.UtilityFactory;
import view.ViewData;

public class ResetEvent implements ToolBarButtonEvent{
	
	private ViewData myData;
	
	public ResetEvent(UtilityFactory utilF, ViewData dataIn){
		myData = dataIn;
	}

	@Override
	public void event() {
		myData.removePlacedEntities(myData.getCurrentLevel());
	}
}