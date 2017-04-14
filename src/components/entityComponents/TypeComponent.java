package components.entityComponents;

import components.IComponent;

public class TypeComponent implements IComponent {
	private EntityType typeOfEntity;

	public TypeComponent(EntityType type) {
		typeOfEntity = type;
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