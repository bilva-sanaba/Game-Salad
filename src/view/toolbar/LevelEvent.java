package view.toolbar;

import data_interfaces.GameSavingDataTool;
import view.UtilityFactory;
import view.ViewData;
import view.window.SplashScreenBuilderWindow;

public class LevelEvent extends GameSavingDataTool implements ToolBarButtonEvent {
	private ViewData myData;
	
	public LevelEvent(UtilityFactory utilF, ViewData data) {
		myData = data;
	}
	
	@Override
	public void event() {
		SplashScreenBuilderWindow window = new SplashScreenBuilderWindow();
		window.openWindow();
	}
}
