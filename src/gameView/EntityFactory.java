package gameView;

import entity.Entity;

public class EntityFactory {

	public EntityFactory() {
	}

	public Entity genEntity() {
		// use reflection to create entity
		Class<?> clazz = Class.forName("entities." + className);
		try {
			return (Entity) clazz.getDeclaredConstructor(parameterTypes)
					.newInstance(indexedParameters);
		} catch (Exception e) {
			return null;
		}
	}
}
