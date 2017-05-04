package view.toolbar;

import java.io.File;

import view.ImageChooser;
import view.UtilityFactory;
import view.ViewData;

public class BackgroundEvent implements ToolBarButtonEvent {
	
	private ViewData myData;
	
	public BackgroundEvent(UtilityFactory utilF, ViewData myD) {
		myData = myD;
	}

	@Override
	public void event() {
		ImageChooser ic = new ImageChooser();
		String filePath = ic.chooseFile();
		
		myData.getLevelEntity(1).setBackgroundFilePath(filePath);
		myData.refresh();
	}
}
