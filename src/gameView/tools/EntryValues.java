package gameView.tools;

import java.util.HashMap;

public class EntryValues {

	private HashMap<String, String> myValues;
	
	public EntryValues(String... titles) {
		myValues = new HashMap<String, String>();
		makeMap(titles);
	}
	
//	public String getTitle(int k) {
//		return retrieve(myTitles, k);
//	}
	
	public String getValue(String title) {
		return retrieve(myValues, title);
	}
	
	private String retrieve(HashMap<Integer, String> myMap, String title) {
		if (myMap.containsKey(title)) {
			return myMap.get(title);
		} else {
			return "";
		}
	}
	
	private void makeMap(String... titles) {
		myValues.put(1, "myFirstValue");
		myValues.put(2, "mySecondValue");
		int count = 1;
		for (String each: titles) {
			myTitles.put(count, each);
			count++;
		}
	}
}
