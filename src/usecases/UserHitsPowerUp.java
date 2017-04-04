package usecases;

import controller.Controller;
import gameEngine_interface.GameEngine;

/**
 * Example Code: User hits powerup, the image must change and the player must get new functionality
 * 
 * The actual code for this will be the exact same as the UseCase: HitEnemyUserDies, however this provides a more detailed idea
 * albeit without concrete implementations of each interface
 * @author Bilva
 *
 */
public class UserHitsPowerUp {
	private GameEngine g = new GameEngine();
	private Controller c = new Controller();
	UserHitsPowerUp(){
	/**
	 * In the controller, the UIImageModel is checked to see if there is a collision
	 * If there is the interaction is handled by the GameEngine
	 */
	if (c.checkCollision(new UIImageModel())){
			GameEngine.handleInteraction(UIView.currentCollision(),UIView.activeObjects());
	}
	/**
	 * Loops through all active objects and for each object finds the appropriate model
	 * within each model looks at each rule and checks if the rule should be run
	 * runs rule with Rule Handler
	 */
	GameEngine.handleInteraction(UIView.currentCollision(),UIView.activeObjects());
		for (UIImagePropertie u : allUIImageProperties){
			currentModel = GameEngine.ModelMap.get(allUIImageProperties.getIdentifier());
			for (Rule r: currentModel.Rules){
				if (Rule.check(InteractingObjects)){
					RuleHandler.runRule(rule,GameEngine.ModelMap,allUIImageProperties)
				}
			}
		}
	}
	/**
	 * This method will change the gamestate objects which the rule has instances of stored through serialization
	 */
	RuleHandler.runRule(rule,ModelMap,allUIImageProperties){
		rule.runRule(ModelMap,allUIImageProperties);
	}
	/**
	 * Loops through each model and if the rule affects the model it updates the model
	 * Then if a change was made the mdoel updates the corresponding appropriate UIImageProperties with new data 
	 */
	Rule.runRule(ModelMap,allUIImageProperties){
		for (Model m : ModelMap.keySet()){
			if (Rule.affects(Model)){
				Rule.update(Model);
				for (UIImageProperties u : allUIImageProperties){
					if Model.getString()== u.getString(){
						Model.update(u);
					}
				}
			}
		}
	}
	/**
	 * Adds a new rule to model for doing something when a KeyCode is inputted
	 */
	Rule.update(Model){
		Model.addRule(new ObjectCreationRule(KeyCode Space, new Sprite(Fireball)));
	}
	/**
	 * Modifies the UIImageProperty so that it corresponds the next state
	 */
	Model.update(UIImageProperties){
		UIImageProperties = Model.currentProperties(); 
	}
}
