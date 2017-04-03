//package gameEngine_interface;
//
//import java.util.Collection;
//import java.util.List;
//
//import gameView.UIImageProperty;
//
///**
// * Class created in the GameAuthoringEnvironment which contains all rules and has a method to change the Sprite by evaluating its
// * rules. 
// * @author Bilva
// *
// */
//public interface Entity {
//	/**
//	 * Takes parameters of interactingObjects to determine what rules should be followed, changes sprites with new rules if applicable
//	 * also changes UIImageProperties appropriately. 
//	 * @param interactingObjects
//	 * @param allActive
//	 * @param allSprites
//	 */
//	public void evaluateKeyRules(Collection<Object> interactingObjects, Collection<UIImageProperty> allActive, Collection<Entity> allSprites);
//	/**
//	 * returns a list of Rules that the Sprite contains
//	 */
//	public List<Component> getRules();
//	
//	public void setNewLocation();
//}
