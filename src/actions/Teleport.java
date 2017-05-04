package actions;

import java.util.List;

import class_annotations.BottomAction;
import class_annotations.LeftAction;
import class_annotations.RightAction;
import class_annotations.TopAction;
import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import entity.IEntity;
import entity.IEntityManager;
import exceptions.InputException;
import gamedata.IRestrictedGameData;

@TopAction()
@LeftAction()
@BottomAction()
@RightAction()
public class Teleport extends AbstractAction  implements IAction {
	private double teleportXLocation;
	private double teleportYLocation;
	
	public Teleport(double newX, double newY) {
		System.out.println("was created");
		teleportXLocation = newX;
		teleportYLocation = newY;
	}

	public Teleport(List<String> inputs) throws InputException {
		String[] loc = inputs.get(0).split(",");
		teleportXLocation = super.parseDouble(loc[0]);
		teleportYLocation = super.parseDouble(loc[1]);
	}
	
	
	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM, IRestrictedGameData currentGameData) {
		((LocationComponent) other.getComponent(ComponentType.Location)).setXY(teleportXLocation, teleportYLocation);
		other.changed(other);
		return getGameDataFactory().blankEntityData(currentGameData);
	}

	

}
