package components.keyExpressions;

import components.AccelerationComponent;
import components.ComponentType;
import components.KeyExpression;
import components.LocationComponent;
import components.TerminalVelComponent;
import components.VelocityComponent;
import entity.IEntity;
import javafx.scene.input.KeyCode;

public enum ConcreteKeyExpressions {
	JUMP ((a) -> {
		VelocityComponent vc = (VelocityComponent) a.getComponent(ComponentType.Velocity);
		AccelerationComponent ac = (AccelerationComponent) a.getComponent(ComponentType.Acceleration);
		if (vc.getY()==0){
			vc.setY(-3);
			ac.setY(.05);
		}
	}),
	RIGHT ((a) -> {
		/*LocationComponent lc = (LocationComponent) a.getComponent(ComponentType.Location);
		lc.setX(lc.getX()+2);*/
		AccelerationComponent ac = (AccelerationComponent) a.getComponent(ComponentType.Acceleration);
		TerminalVelComponent tvc = (TerminalVelComponent) a.getComponent(ComponentType.TerminalVelComponent);
		VelocityComponent vc = (VelocityComponent) a.getComponent(ComponentType.Velocity);
		if(tvc.canAccelerateX(vc.getX())){
			ac.setX(1);
			System.out.println(vc.getX());
		}
		else{
			vc.setX(tvc.getX());
			ac.setX(0);
			System.out.println(vc.getX());
		}
		
	}),
	LEFT ((a) -> {
		/*LocationComponent lc = (LocationComponent) a.getComponent(ComponentType.Location);
		lc.setX(lc.getX()-2);*/
		AccelerationComponent ac = (AccelerationComponent) a.getComponent(ComponentType.Acceleration);
		TerminalVelComponent tvc = (TerminalVelComponent) a.getComponent(ComponentType.TerminalVelComponent);
		VelocityComponent vc = (VelocityComponent) a.getComponent(ComponentType.Velocity);
		if(tvc.canAccelerateX(vc.getX())){
			ac.setX(-1);
		}
		else{
			ac.setX(0);
		}
	});

    private KeyExpression keyExpression; 

    ConcreteKeyExpressions(KeyExpression ke) {
        this.keyExpression = ke;
    }
    
    public KeyExpression getKeyExpression(){
    	return keyExpression;
    }

}
