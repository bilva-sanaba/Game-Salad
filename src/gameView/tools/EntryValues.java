package gameView.tools;

import java.util.HashMap;

public class EntryValues {

	private HashMap<Integer, String> myValues;
	
	public EntryValues() {
		myValues = new HashMap<Integer, String>();
		makeMap();
	}
	
	public String getValue(int k) {
		return retrieve(myValues, k);
	}
	
	private String retrieve(HashMap<Integer, String> myMap, int k) {
		if (myMap.containsKey(k)) {
			return myMap.get(k);
		} else {
			return "";
		}
	}
	
	private void makeMap() {
		myValues.put(1, "myFirstValue");
		myValues.put(2, "mySecondValue");
	}
}
