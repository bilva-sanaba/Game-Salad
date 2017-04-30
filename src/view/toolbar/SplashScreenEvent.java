package view.toolbar;


import entity.SplashData;
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
		SplashData s = ssbw.createEntity();
		System.out.println("wiener");
		System.out.println(s.getGameTitle()+"<- THIS IS THIS THING");

		
		myData.setSplashEntity(new SplashData(0, s.getGameTitle(), s.getInstructions(), s.getBackgroundFilePath()));
	}
}