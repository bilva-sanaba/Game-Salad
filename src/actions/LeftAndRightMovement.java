// This entire file is part of my masterpiece
// BELAL TAHER
// This is the simple left and right movement. I simply added this in so you could see where the functionality
// of the AI engine got moved to. In the previous version of my AI engine, all this code would be in the
// update method in some if block. On top of all the reasons I stated in the AIEngine class' comment, that 
// would be bad design because it violates the general public method and compartmentalized private method
// convention that displays good design. Instead of having to navigate through ugly if trees to edit functionality,
// all a user has to do now is refer to the map in the AI engine class and go to the respective action that they want
// to edit.

package actions;

import components.entityComponents.ComponentType;
import components.entityComponents.StepComponent;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

public class LeftAndRightMovement extends AbstractAction implements IAction{

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		
		StepComponent mySteps = (StepComponent) self.getComponent(ComponentType.Step);
		VelocityComponent myVelocity = (VelocityComponent) self.getComponent(ComponentType.Velocity);
		
		if(mySteps.getStepLeft() == 0){
			mySteps.setStep(mySteps.getTotalStep());
			myVelocity.setX(myVelocity.getX() * -1);
		}
		else{
			mySteps.takeStep();
		}
		
		return getGameDataFactory().blankEntityData(currentGameData);
	}

}
