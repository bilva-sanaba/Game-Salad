package entity.presets;

import entity.Entity;

public class AbstractPlayer extends Entity {

	public AbstractPlayer(int id) {
		super(id);
		addCollisionComponents();
	}

	private void addCollisionComponents() {
		
	}
}