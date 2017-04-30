package view.toolbar;

import java.io.File;

import view.ImageChooser;
import view.ViewData;

public class BackgroundEvent implements ToolBarButtonEvent {
	
	private ViewData myData;
	
	public BackgroundEvent(ViewData myD) {
		myData = myD;
	}

	@Override
	public void event() {
		ImageChooser ic = new ImageChooser();
		String filePath = ic.chooseFile();
		
		myData.getLevelEntity().setBackgroundFilePath(filePath);
		myData.refresh();
	}

}
