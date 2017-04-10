package controller_interfaces;

import java.io.File;

/**
 * This class is in charge of taking in the XML file, and parsing it according
 * to what needs to go to the front end to be displayed (file path for image, x,
 * y, height, width) and the backend to set up all of the settings (mainly, it
 * will pass a map back with strings mapping to all the relative models the
 * game).
 * 
 * @author Henry
 *
 */
public interface XMLInterpreterInterface {

	/**
	 * Parse the XML file to relative modules
	 * 
	 * @param input
	 *            - XML file
	 */
	public void parseData(File input);
}
