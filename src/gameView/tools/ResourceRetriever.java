package gameView.tools;

import gameView.UIView;

import java.util.ResourceBundle;

public class ResourceRetriever {

	public ResourceRetriever() {
		
	}
	
	public String getStyleSheets(Object obj, String name) {
		return obj.getClass().getResource("/" + UIView.DEFAULT_LOCATION + 
				ResourceBundle.getBundle(UIView.DEFAULT_LOCATION+UIView.DEFAULT_STYLING).getString(name)).toExternalForm();
	}
}


