package data_interfaces;

import java.util.ArrayList;

public interface XMLParser extends FileLoader {
	public ArrayList getMapData(String fileName);
	public ArrayList getInstructionData(String fileName);

}
