package engines;

import java.util.ArrayList;

import components.IComponent;

public class EngineData {

	ArrayList<IComponent> myComponents = new ArrayList<IComponent>();

	public EngineData(ArrayList<IComponent> aMyComponents) {
		myComponents = aMyComponents;
	}

}
