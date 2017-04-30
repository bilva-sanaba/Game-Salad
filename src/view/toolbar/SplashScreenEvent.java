package view.toolbar;


import entity.SplashEntity;
import view.UtilityFactory;
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
		SplashEntity s = ssbw.createEntity();
		System.out.println("wiener");
		System.out.println(s.getGameTitle()+"<- THIS IS THIS THING");

		
		myData.setSplashEntity(new SplashEntity(s.getID(), s.getGameTitle(), s.getInstructions(), s.getBackgroundFilePath()));
	}
}