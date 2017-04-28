package engines;

import java.util.Collection;
import java.util.List;

import components.entityComponents.ComponentType;
import components.entityComponents.StepComponent;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;

public class AIEngine extends AbstractEngine{

	public AIEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
	}

	@Override
	protected List<ComponentType> neededComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRestrictedGameData update(Collection<KeyCode> keysPressed, IRestrictedGameData currentGameData) {
		for(IEntity e: getEManager().getEntities()){
			if(hasComponent(e, ComponentType.Step)){
				StepComponent sc = (StepComponent) e.getComponent(ComponentType.Step);
				VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
				sc.takeStep();
				if(sc.getStepLeft() <= 0){

					vc.setX(-vc.getX()); //TODO: USE MONSTER MOVEMENT PATTERN TO MAKE THIS BETTER
					sc.setStep(sc.getTotalStep());
				}
			}
		}
		return currentGameData;
	}

}
