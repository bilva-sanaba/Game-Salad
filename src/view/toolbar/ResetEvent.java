package view.toolbar;

import view.ViewData;

public class ResetEvent implements ToolBarButtonEvent{
	
	private ViewData myData;
	
	public ResetEvent(ViewData dataIn){
		myData = dataIn;
	}

	@Override
	public void event() {
		myData.removePlacedEntities();
	}

}