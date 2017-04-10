package components.keyExpressions;

import components.ComponentType;
import components.KeyExpression;
import components.VelocityComponent;
import entity.IEntity;
import javafx.scene.input.KeyCode;

public enum ConcreteKeyExpressions {
	JUMP ((a) -> {
		VelocityComponent vc = (VelocityComponent) a.getComponent(ComponentType.Velocity);
		if (vc.getY()==0){
			vc.setY(50);
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
