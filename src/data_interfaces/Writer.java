package data_interfaces;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import entity.Entity;
import entity.IEntity;

public interface Writer {
	/**
	 * Writes to an XML file with the given name
	 * the collection which it is passed
	 * @param fileName the name of the file without prefix/suffix
	 * @param gameData the data to be saved
	 */
	public void writeFile(String fileName, Collection gameData);
}
