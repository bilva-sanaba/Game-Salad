package usecases;
/**
 * Example Code: User hits powerup, the image must change and the player must get new functionality
 * @author Bilva
 *
 */
public class UserHitsPowerUp {
	if (Controller.checkCollision(UIView)){
			GameEngine.handleInteraction(UIView.currentCollision(),UIView.activeObjects());
	}
	GameEngine.handleInteraction(InteractingObjects, allUIImageProperties){
		for (UIImagePropertie u : allUIImageProperties){
			currentModel = GameEngine.ModelMap.get(allUIImageProperties.getIdentifier());
			for (Rule r: currentModel.Rules){
				if (Rule.check(InteractingObjects)){
					RuleHandler.runRule(rule,GameEngine.ModelMap,allUIImageProperties)
				}
			}
		}
	}
	RuleHandler.runRule(rule,ModelMap,allUIImageProperties){
		rule.runRule(ModelMap,allUIImageProperties);
	}
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
	Rule.update(Model){
		Model.addRule(new ObjectCreationRule(KeyCode Space, new Sprite(Fireball)));
	}
	Model.update(UIImageProperties){
		UIImageProperties = Model.currentProperties(); 
	}
}
