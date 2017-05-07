//This entire file is part of my masterpiece
//Jack Bloomfeld
package view.commands;

import components.entityComponents.LocationComponent;
import entity.Entity;
import view.ViewData;

/**
 * Defines the paste command
 * @author Jack
 *
 */
public class PasteCommand implements RightClickEvent{

private ViewData myData;
private double x;
private double y;
private Entity pastedEntity;
	
	public PasteCommand(ViewData data, Entity entity, double xIn, double yIn){
		myData = data;
		x = xIn;
		y = yIn;
	}
	
	@Override
	public void execute() {
		pastedEntity = myData.getCopiedEntity().newCopy(myData.getPlacedEntityID());
		pastedEntity.addComponent(new LocationComponent(x, y));
		myData.placeEntity(myData.getCurrentLevel(), pastedEntity);
		myData.addEvent(this);
	}
}
