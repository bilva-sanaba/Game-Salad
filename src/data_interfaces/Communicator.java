package data_interfaces;

import java.util.Collection;

import org.w3c.dom.Element;

import entity.*;

public class Communicator extends GameSavingDataTool {

	private String fileName;

	public Communicator(String s) {
		fileName = getPrefix() + s + getSuffix();

	}

	public Collection<Entity> getData() {
		XMLParser xp = new XMLParser();
		EntityHandler eh = new EntityHandler();
		return eh.getCollection(xp.getData(fileName));
	}
}
