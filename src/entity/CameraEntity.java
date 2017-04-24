package entity;

import java.util.Collection;

import components.IComponent;
import components.entityComponents.ComponentType;

public class CameraEntity extends Entity{

	public CameraEntity(int id) {
		super(id);
		this.addComponent(new CameraComponent);
	}
	
	
	
	

	
}
