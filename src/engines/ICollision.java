package engines;



public interface ICollision {

	/**
	 * This method runs through the entities in the game and checks for collisions. A good implementation will also have an IEntityManager as an instance variable. This entity manager gives access to entities and their components.
	 * Method is void since the ICollision engine will pass on colliding objects to other classes to handle.
	 */
	public void checkCollisionsOccurred();
	
	
}
