package view.toolbar;


import entity.SplashEntity;
import view.SplashScreenBuilderWindow;
import view.ViewData;

public class SplashScreenEvent implements ToolBarButtonEvent {

	private ViewData myData;
	
	public SplashScreenEvent(ViewData vd) {
		myData = vd;
	}
	
	@Override
	public void event() {
		SplashScreenBuilderWindow ssbw = new SplashScreenBuilderWindow();
		SplashEntity s = ssbw.openWindow();
		System.out.println(s.getDisplayName()+"<- THIS IS THIS THING");
		
		myData.setSplashEntity(new SplashEntity(s.getID(), s.getDisplayName(), s.getInstructions(), s.getBackgroundFilePath()));
	}
}