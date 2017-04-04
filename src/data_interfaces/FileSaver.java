package data_interfaces;

public interface FileSaver {
	/**
	 * Creates the file with a given object and given name
	 * @param fileName the name
	 * @param data what is sent to be saved
	 */
	public void createFile(String fileName, Object data);
}
