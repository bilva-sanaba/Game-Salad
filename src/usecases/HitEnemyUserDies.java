package usecases;

public class HitEnemyUserDies {

	/**
	 * This is the first step in the game loop: checking to see if any
	 * collisions have occurred. This happens in the Collision Handler which
	 * returns the colliding Sprites to the Controller and then to the backend.
	 * The Collision Handler will use the Controller's handleInteraction method
	 * after detecting a collision.
	 */
	public void detectCollision() {
		CollisionHandler ch = new CollisionHandler();
		Entity collider = ch.detectCollision();
		Entity collidee = ch.detectCollision();
		handleInteraction(collider, collidee);
	}

	/**
	 * This happens in the Game Engine, which the Controller calls. The Game
	 * Engine will get the rules for each colliding object and then executes the
	 * relevant rules, which is defined within each rule. Then the Game Engine
	 * will pass on to the Physics Engine which will execute the necessary
	 * methods based off the objects' rules.
	 * 
	 * @param collider
	 * @param collidee
	 */
	public void handleInteraction(Entity collider, Entity collidee) {
		Component colliderRule = collider.getRules().get(0);
		Component collideeRule = collidee.getRules().get(0);
		colliderRule.checkNeededObjects();
		collideeRule.checkNeededObjects();
		colliderRule.runRule();
		collideeRule.runRule();
		handlePhysics(collider, collidee);
	}

	/**
	 * The Physics Engine will update location in the Sprite's backend model,
	 * which is binded with the UIimages. Then, the display is automatically
	 * updated and the sequence of calls comes to an end.
	 * 
	 * @param collider
	 * @param collidee
	 */
	public void handlePhysics(Entity collider, Entity collidee) {
		collider.setNewLocation();
		collidee.setNewLocation();
	}

}
