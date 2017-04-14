package gameView.tools;

import gameView.UIView;

import java.util.ResourceBundle;

public class ResourceRetriever {

	public ResourceRetriever() {
		
	}
	
	/**
	 * Retrieves the string of all the associated values given a specific key from a .properties file
	 * @param obj - object calling for the resources
	 * @param name - name of the corresponding key
	 * @return
	 */
	public String getStyleSheets(Object obj, String name) {
		return obj.getClass().getResource("/" + UIView.DEFAULT_LOCATION + 
				ResourceBundle.getBundle(UIView.DEFAULT_LOCATION+UIView.DEFAULT_STYLING).getString(name)).toExternalForm();
	}
}


