package data_interfaces;

public interface FileLoader {
	/**
	 * Loads the wanted file without giving a usser interface
	 * @param fileName the name of the file you want to load
	 * @return the needed file
	 */
	public Object loadFile(String fileName);
}
