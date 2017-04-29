package data_interfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.w3c.dom.Element;

import entity.*;

public class Communicator extends GameSavingDataTool implements EncapsulatedCommunication {

	private String fileName;

	public Communicator(String s) {
		fileName = getPrefix() + s + getSuffix();

	}

	public Collection<Entity> getData() {
		XMLParser xp = new XMLParser();
		EntityHandler eh = new EntityHandler();
		return eh.getCollection(xp.getData(fileName));
	}
	public List<IEntityManager> futureGetData() {
		List<IEntityManager> x = new ArrayList<IEntityManager>();
		return x;
		
	}
}
