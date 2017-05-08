// This entire file is part of my masterpiece
// BELAL TAHER
// This class is actually what I refactored after the end of the project. The original way that this class
// was designed was terrible. It was essentially a bunch of if statements checking what components
// and entity had and applying actions according to that. That's extremely inflexible code, however, and 
// doesn't take advantage of all the components of the project we implemented. Instead of using a disgusting
// if tree, I refactored this code to cold a map of enumerated monster types to IActions. What the code does 
// now is functionally the same as before, but it's much more concise. It also makes the code much
// more readable in my opinion since you don't have to follow some ugly if tree to figure out what happens
// to an enemy who has certain components. Instead you can simply refer to the map that's constructed
// in this engine's constructor to figure out what action you should look for. What's nice about this 
// new implementation is that it takes advantage of the IAction interface that we made. It makes the project
// feel much more cohesive since it's now actually using functionality that we implemented instead of
// acting independently. This is also good because let's say we wanted to add some functionality in the future
// that cancelled all current actions. If we had the AI engine coded the other way, the AI would continue to 
// operate when they shouldn't. Essentially, the reason I think this class is a good addition to my masterpiece 
// is because it displays how aware I am of potential issues we could've ran into with the previous design.
// Whenever I code now, I keep flexibility and future issues in mind; something I never did before taking this class.
// I would honestly say that's the biggest take away I've had from this course because it has made me code extensible
// and able to tackle new issues a lot better than before. This version of my AI engine is also a lot more concise, which
// also adds to readability. 

package engines;

import java.util.Collection;
import java.util.HashMap;

import actions.AbstractAction;
import actions.AggressiveEnemyAction;
import actions.IAction;
import actions.LeftAndRightMovement;
import actions.UpAndDownMovement;
import components.entityComponents.ComponentType;
import components.entityComponents.MonsterType;
import components.entityComponents.MonsterTypeComponent;
import components.entityComponents.StepComponent;
import components.entityComponents.TypeComponent;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;

/**
 * AI engine which updates enemy behavior using various alogrithms that utilize each monster's enumerated type, velocity, and 
 * location
 * @author Belal
 *
 */

public class AIEngine extends AbstractEngine{
	
	private HashMap<MonsterType, IAction> typeToActionMap;

	public AIEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
		typeToActionMap.put(MonsterType.LeftAndRight, new LeftAndRightMovement());
		typeToActionMap.put(MonsterType.UpAndDown, new UpAndDownMovement());
		typeToActionMap.put(MonsterType.AggressiveChaser, new AggressiveEnemyAction());
	}

	@Override
	public IRestrictedGameData update(Collection<KeyCode> keysPressed, IRestrictedGameData currentGameData) {
		for(IEntity e: getEManager().getEntities()){
			
			if(hasComponent(e, ComponentType.MonsterType)) {
				MonsterTypeComponent mtc = (MonsterTypeComponent) e.getComponent(ComponentType.MonsterType);
				IAction requiredAction = typeToActionMap.get(mtc);
				requiredAction.executeAction(null, e, myEManager, currentGameData);
			}
		}
		return currentGameData;
	}

}
