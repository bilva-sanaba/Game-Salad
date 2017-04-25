package components.entityComponents;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import entity.IEntity;
import javafx.scene.input.KeyCode;

public enum ConcreteKeyExpressions {
	JUMP ((a) -> {
//		ScriptEngine engine = new ScriptEngineManager().getEngineByName("groovy");
		VelocityComponent vc = (VelocityComponent) a.getComponent(new VelocityComponent(0,0));
		AccelerationComponent ac = (AccelerationComponent) a.getComponent(ComponentType.Acceleration);
		if (vc.getY()==0){
			vc.setY(-5);
			ac.setY(.1);
		}
//		try {
//			engine.put("vc", vc);
//			engine.put("ac", ac);
//			engine.eval("if (vc.getY()==0) { vc.setY(-3) ; ac.setY(0.05) }");
//		} catch (ScriptException ex) {
//            System.out.println(ex);
//        }
	}),
	RIGHT ((a) -> {
		AccelerationComponent ac = (AccelerationComponent) a.getComponent(ComponentType.Acceleration);
		ac.setX(0.4);

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
		AccelerationComponent ac = (AccelerationComponent) a.getComponent(ComponentType.Acceleration);
		ac.setX(-0.4);
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
