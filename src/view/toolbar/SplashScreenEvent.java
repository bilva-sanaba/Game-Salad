package view.toolbar;

import view.UtilityFactory;
import entity.SplashData;
import view.ViewData;
import view.window.*;

public class SplashScreenEvent implements ToolBarButtonEvent {

	private ViewData myData;
	private UtilityFactory myUtilF;
	
	public SplashScreenEvent(UtilityFactory utilF, ViewData vd) {
		myData = vd;
		myUtilF = utilF;
	}
	
	@Override
	public void event() {
		SplashScreenBuilderWindow ssbw = new SplashScreenBuilderWindow(myUtilF);
		SplashData s = ssbw.createEntity();
		
		myData.setSplashEntity(new SplashData(0, s.getGameTitle(), s.getInstructions(), s.getBackgroundFilePath()));
	}
}