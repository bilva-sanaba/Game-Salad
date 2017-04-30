package entity.presets;

import components.entityComponents.AccelerationComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.VelocityComponent;
import entity.Entity;

public class AbstractPlayer extends Entity {
//	CharacterRadioButton = Label, ImageProperties, Health, Strength, KeyInput, Lives, Collidable, Velocity, Acceleration
	public AbstractPlayer(int id) {
		super(id);
		addCollisionComponents();
	}

	private void addCollisionComponents() {
		this.addComponent(new LabelComponent("Defult Player"));
		this.addComponent(new VelocityComponent(0, 0));
		this.addComponent(new AccelerationComponent(0, 0));
	}
}