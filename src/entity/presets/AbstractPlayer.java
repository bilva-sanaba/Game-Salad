package entity.presets;

import components.entityComponents.AccelerationComponent;
import components.entityComponents.AcheivementAction;
import components.entityComponents.ControllableComponent;
import components.entityComponents.KeyInputComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.VelocityComponent;
import entity.Entity;
import javafx.scene.input.KeyCode;

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
		KeyInputComponent k = new KeyInputComponent();
		k.addToMap(KeyCode.W, new AcheivementAction());
		this.addComponent(k);
		this.addComponent(new ControllableComponent());
	}
}