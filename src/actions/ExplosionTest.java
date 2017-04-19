package actions;

import static org.junit.Assert.*;

import org.junit.Test;

import entity.Entity;

public class ExplosionTest {

	@Test
	public void testImageLoads() {
		Explosion ex = new Explosion();
		Entity e = new Entity(0);
		ex.executeAction(e);
	}

}
