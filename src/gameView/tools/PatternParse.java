package gameView.tools;

/***
 * CODE BASED OFF OF CODE PRESENTED BY ROBERT DUVALL IN DUKE'S CS308 LECTURE
 */
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

public class PatternParse {
	private List<Entry<String, Pattern>> mySymbols;

	public PatternParse() {
		mySymbols = new ArrayList<Entry<String, Pattern>>();
	}

	public void addPattern(String pattern) {
		ResourceBundle resource = ResourceBundle.getBundle(pattern);
		Enumeration<String> iterator = resource.getKeys();
		while (iterator.hasMoreElements()) {
			String key = iterator.nextElement();
			String regex = resource.getString(key);
			mySymbols.add(new SimpleEntry<>(key, Pattern.compile(regex,
					Pattern.CASE_INSENSITIVE)));
		}
	}

	public String getSymbol(String text) {
		final String ERROR = "NO MATCH";
		for (Entry<String, Pattern> e : mySymbols) {
			if (e.getValue().matcher(text).matches()) {
				return e.getKey();
			}
		}
		return ERROR;
	}
}
