package components.entityComponents;

import components.AComponent;
import components.IComponent;

public class InfiniteBlockActionComponent extends AComponent implements IComponent{
private BlockAction


@Override
public ComponentType getComponentType() {
	return ComponentType.InfiniteBlockAction;
}

@Override
public IComponent newCopy() {
	return new InfiniteBlockActionComponent();
}
