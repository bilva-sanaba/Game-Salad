package entity;

import java.util.ArrayList;
import java.util.List;

import components.ComponentType;
import components.IComponent;

public interface IEntityManager {

	public List<ArrayList<IComponent>> getAllComponents(List<ComponentType> neededComponents);

}
