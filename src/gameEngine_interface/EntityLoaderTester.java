package gameEngine_interface;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import entity.Entity;
import entity.EntityManager;
import entity.IEntity;
import entity.IEntityManager;

public class EntityLoaderTester {

	@Test
	public void test() {
		IEntityManager main = new EntityManager(new ArrayList<IEntity>());
		IEntityManager newEM = new EntityManager(new ArrayList<IEntity>());
		newEM.getEntities().add(new Entity(20));	
		EntityLoader el = new EntityLoader(main);
		el.loadNew(newEM);
		}

}
