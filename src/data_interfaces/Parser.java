package data_interfaces;

import java.util.List;

import org.w3c.dom.Element;

public interface Parser {
	/**
	 * Parses the file to return the needed list of objects
	 * @param fileName The name of the file without prefix or suffix
	 * @return the list of objects
	 */
	public List getData(String fileName);
}
