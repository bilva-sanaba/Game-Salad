package components.keyExpressions;

import components.AccelerationComponent;
import components.ComponentType;
import components.KeyExpression;
import components.LocationComponent;
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
		LocationComponent vc = (LocationComponent) a.getComponent(ComponentType.Location);
		vc.setX(vc.getX()+1);
	}),
	LEFT ((a) -> {
		LocationComponent vc = (LocationComponent) a.getComponent(ComponentType.Location);
		vc.setX(vc.getX()-1);
	});

    private KeyExpression keyExpression; 

    ConcreteKeyExpressions(KeyExpression ke) {
        this.keyExpression = ke;
    }
    
    public KeyExpression getKeyExpression(){
    	return keyExpression;
    }

}
