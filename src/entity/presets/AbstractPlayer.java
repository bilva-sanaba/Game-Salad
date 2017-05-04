package entity.presets;

import actions.AcheivementAction;
import actions.ShootAction;
import components.entityComponents.AccelerationComponent;
import components.entityComponents.CheckCollisionComponent;
import components.entityComponents.CollidableComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.ControllableComponent;
import components.entityComponents.EntityType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.KeyInputComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.TimeComponent;
import components.entityComponents.TypeComponent;
import components.entityComponents.VelocityComponent;
import components.keyExpressions.JumpAction;
import components.keyExpressions.LeftAction;
import components.keyExpressions.RightAction;
import entity.Entity;
import javafx.scene.input.KeyCode;

public class AbstractPlayer extends Entity {
//	CharacterRadioButton = Label, ImageProperties, Health, Strength, KeyInput, Lives, Collidable, Velocity, Acceleration
	public AbstractPlayer(int id) {
		super(id);
		System.out.println("player is made");
		addCollisionComponents();
		this.addComponent(new TimeComponent());
	}

	private void addCollisionComponents() {
		this.addComponent(new CheckCollisionComponent(true));
		this.addComponent(new VelocityComponent(0, 0));
		this.addComponent(new AccelerationComponent(0, 0));
		KeyInputComponent k = new KeyInputComponent();
		k.addToMap(KeyCode.W, new JumpAction());
		k.addToMap(KeyCode.A, new LeftAction());
//		k.addToMap(KeyCode.S, new DownAction());
		k.addToMap(KeyCode.D, new RightAction());
		this.addComponent(k);
		this.addComponent(new ControllableComponent(true));
		this.addComponent(new TypeComponent(EntityType.Player));
		this.addComponent(new CollidableComponent(true));
		this.addComponent(new ImagePropertiesComponent(50,50));
	}
}