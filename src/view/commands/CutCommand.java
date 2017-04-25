package view.commands;

import view.GridView;
import view.ViewData;

public class CutCommand implements RightClickEvent{

private ViewData myData;
	
	public CutCommand(ViewData data){
		myData = data;
	}
	
	@Override
	public void execute() {
		myData.unplaceEntity();
		myData.setUserSelectedEntity(null);
	}

}
