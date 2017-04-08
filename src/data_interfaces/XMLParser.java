package data_interfaces;


import java.util.List;
import java.io.*;


import entity.IEntityManager;



public class XMLParser implements FileLoader {

	@Override

	public Object loadFile(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			StringBuilder sb = new StringBuilder();
			String temp = reader.readLine();
			while (temp != null) {
				sb.append(temp);
				temp = reader.readLine();
			}
			return sb.toString();
		}
		catch (IOException e) {
			//TODO 
		}
		return null;
	}
	
	/**
	 * Retrieves the data needed from the file
	 * to be sent to the game engine
	 * @param fileName the name of the file
	 * @return the list of reflected objects
	 */
	/*public List getData(String fileName) {
		
	}*/

	public static void main(String [] args) {
		XMLParser x = new XMLParser();
		System.out.println(x.loadFile("games/rub.txt"));
	}
}
