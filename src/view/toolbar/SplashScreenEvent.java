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
//		SplashEvent s = 
		ssbw.openWindow();
		
//		myData.setSplashEntity(s);
	}
}