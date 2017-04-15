package view.toolbar;


import entity.SplashEntity;
import view.ViewData;
import view.window.*;

public class SplashScreenEvent implements ToolBarButtonEvent {

	private ViewData myData;
	
	public SplashScreenEvent(ViewData vd) {
		myData = vd;
	}
	
	@Override
	public void event() {
		SplashScreenBuilderWindow ssbw = new SplashScreenBuilderWindow();
		SplashEntity s = ssbw.createEntity();
		System.out.println(s.getGameTitle()+"<- THIS IS THIS THING");

		
		myData.setSplashEntity(new SplashEntity(s.getID(), s.getGameTitle(), s.getInstructions(), s.getBackgroundFilePath()));
	}
}