package view.toolbar;


import entity.SplashEntity;
import view.ViewData;
import view.window.SplashScreenBuilderWindow;

public class SplashScreenEvent implements ToolBarButtonEvent {

	private ViewData myData;
	
	public SplashScreenEvent(ViewData vd) {
		myData = vd;
	}
	
	@Override
	public void event() {
		SplashScreenBuilderWindow ssbw = new SplashScreenBuilderWindow();
		SplashEntity s = ssbw.createEntity();
		
		myData.setSplashEntity(s);
	}
}