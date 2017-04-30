package engines;

import java.util.Collection;

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

public class AIEngine extends AbstractEngine{

	public AIEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
	}

	@Override
	public IRestrictedGameData update(Collection<KeyCode> keysPressed, IRestrictedGameData currentGameData) {
		for(IEntity e: getEManager().getEntities()){
			
			if(hasComponent(e, ComponentType.Step)){
				MonsterTypeComponent mtc = (MonsterTypeComponent) e.getComponent(ComponentType.MonsterType);
				StepComponent sc = (StepComponent) e.getComponent(ComponentType.Step);
				VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
				sc.takeStep();
				if(sc.getStepLeft() <= 0 && mtc.getType().equals(MonsterType.LeftAndRight)){
					vc.setX(-vc.getX());
					sc.setStep(sc.getTotalStep());
				}
				else if(sc.getStepLeft() <= 0 && mtc.getType().equals(MonsterType.UpAndDown)){
					vc.setY(-vc.getY());
					sc.setStep(sc.getTotalStep());
				}
			}
		}
		return currentGameData;
	}

}
