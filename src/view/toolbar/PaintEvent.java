package view.toolbar;

import data_interfaces.*;
import view.UtilityFactory;
import view.ViewData;
import voogasalad.util.paint.PaintWindow;

public class PaintEvent extends GameSavingDataTool implements ToolBarButtonEvent {
	
	public PaintEvent(UtilityFactory utilF, ViewData data){
		
	}

	@Override
	public void event() {
		new PaintWindow();
	}
}