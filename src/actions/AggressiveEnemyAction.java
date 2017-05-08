// This entire file is part of my masterpiece
// BELAL TAHER
// The reason I added this class into my masterpiece is because it displays a new type of monster action
// that would not have been possible with my previous implementation. This AI behavior involves following the main
// character around, which is independent of the step component. Previously, the AI engine used the step component
// as an identifier for monsters since all of them would have steps. But this greatly limited how AI could function in 
// our game since all their movement patterns would occur for a set period of time. There was no dynamic aspect
// to their intelligence. By using actions, we're able to make the behavior much more dynamic. In this case, the enemy
// runs towards the player at a given CHASE_SPEED that is specified in the beginning of the class and then jumps up
// at the player to try and attack him. Another benefit of using actions instead of simply hard coding if blocks 
// into the AI engine is that it opens the possibility of an action that involves more entities than just the
// main character the and AI because the IEntity other parameter. Although the fact that it's only 
// one extra entity in the equation kind of limits flexibility, it's definitely more flexible than it was when
// the behavior of AI was hardcoded in if blocks in the update method.  

package actions;

import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import components.entityComponents.StepComponent;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

public class AggressiveEnemyAction extends AbstractAction implements IAction{
	
	public static final int CHASE_SPEED = 7;
	public static final int JUMP_AGGRESSION = 30;
	public static final int JUMP_SPEED_TO_START_CHASING_AGAIN = 0;

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		
		LocationComponent myLocation = (LocationComponent) self.getComponent(ComponentType.Location);
		VelocityComponent myVelocity = (VelocityComponent) self.getComponent(ComponentType.Velocity);
		
		LocationComponent mainPlayerLocation = currentGameData.getMainLocation();
		
		if(myLocation.getX() < mainPlayerLocation.getX() && myVelocity.getY() == JUMP_SPEED_TO_START_CHASING_AGAIN){
			myVelocity.setX(CHASE_SPEED);
		}
		else if(myLocation.getX() > mainPlayerLocation.getX() && myVelocity.getY() == JUMP_SPEED_TO_START_CHASING_AGAIN){
			myVelocity.setX(CHASE_SPEED * -1);
		}
		else if(myLocation.getX() == mainPlayerLocation.getX()){
			myVelocity.setX(0);
			myVelocity.setY(JUMP_AGGRESSION);
		}
		
		return getGameDataFactory().blankEntityData(currentGameData);
	}

}
