package components.entityComponents;

import components.AComponent;
import components.IComponent;

public class TypeComponent extends AComponent implements IComponent {
	private EntityType typeOfEntity;

	public TypeComponent(EntityType type) {
		typeOfEntity = type;
	}
	
	public String getTypeString() {
		return typeOfEntity.toString();
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Type;
		
	}
	
	public EntityType getType() {
		return typeOfEntity;
	}

	@Override
	public IComponent newCopy() {
		return new TypeComponent(getType());
	}

	
	
}
