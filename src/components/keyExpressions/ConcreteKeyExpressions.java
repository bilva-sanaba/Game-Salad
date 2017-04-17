package components.keyExpressions;


import components.entityComponents.ComponentType;
import components.entityComponents.IKeyExpression;
import components.movementcomponents.AccelerationComponent;
import components.movementcomponents.LocationComponent;
import components.movementcomponents.VelocityComponent;
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
		LocationComponent lc = (LocationComponent) a.getComponent(ComponentType.Location);
		lc.setX(lc.getX()+2);
		/*AccelerationComponent ac = (AccelerationComponent) a.getComponent(ComponentType.Acceleration);
		TerminalVelComponent tvc = (TerminalVelComponent) a.getComponent(ComponentType.TerminalVelComponent);
		VelocityComponent vc = (VelocityComponent) a.getComponent(ComponentType.Velocity);
		if(tvc.canAccelerateX(vc.getX())){
			ac.setX(1);
		}
		else{
			vc.setX(tvc.getX());
			ac.setX(0);
		}*/
		
	}),
	LEFT ((a) -> {
		LocationComponent lc = (LocationComponent) a.getComponent(ComponentType.Location);
		lc.setX(lc.getX()-2);
		/*AccelerationComponent ac = (AccelerationComponent) a.getComponent(ComponentType.Acceleration);
		TerminalVelComponent tvc = (TerminalVelComponent) a.getComponent(ComponentType.TerminalVelComponent);
		VelocityComponent vc = (VelocityComponent) a.getComponent(ComponentType.Velocity);
		if(tvc.canAccelerateX(vc.getX())){
			ac.setX(-1);
		}
		else{
			ac.setX(0);
		}*/
	}),
	REMOVE ((a) -> {
		a.changed(null);
	});

    private IKeyExpression keyExpression; 

    ConcreteKeyExpressions(IKeyExpression ke) {
        this.keyExpression = ke;
    }
    
    public IKeyExpression getKeyExpression(){
    	return keyExpression;
    }
}
