package components;

import components.hudComponents.HUDComponentType;

public interface IHUDComponent {
	public HUDComponentType getComponentType();
	
	public IHUDComponent newCopy();
}
