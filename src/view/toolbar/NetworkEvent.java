package view.toolbar;

import view.UtilityFactory;
import view.ViewData;
import view.window.NetworkWindow;

public class NetworkEvent implements ToolBarButtonEvent {

	private ViewData myData;
	private UtilityFactory myUtilF;
	
	public NetworkEvent(UtilityFactory utilF, ViewData data) {
		myUtilF = utilF;
		myData = data;
	}

	@Override
	public void event() {
		NetworkWindow myNetWindow = new NetworkWindow(myUtilF, myData);
		myNetWindow.openWindow();
	}
}
