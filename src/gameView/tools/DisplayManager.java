package gameView.tools;

import gameView.displayComponents.UIDisplayComponent;
import gameView.gameScreen.GameScreen;

import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import voogasalad.util.reflection.Reflection;

public class DisplayManager {

	private static final String COMPONENT_LOCATION = "gameView.displayComponents.";
	private static final String BUNDLE_KEY = DisplayManager.class.getSimpleName();
	
	private HashMap<String, UIDisplayComponent> myAllDisplays;
	private HashMap<String, UIDisplayComponent> myActiveDisplays;
	private GameScreen myScreen;
	private ReadOnlyDoubleProperty myWidthBound;
	private ReadOnlyDoubleProperty myHeightBound;
	
	public DisplayManager(GameScreen screen, String filePath, ReadOnlyDoubleProperty width, ReadOnlyDoubleProperty height) {
		myScreen = screen;
		setBounds(width, height);
		myAllDisplays = makeComponents(filePath);
		myActiveDisplays = new HashMap<String, UIDisplayComponent>();
		copyMap(myAllDisplays);
	}
	
	public void add(String toAdd) {
		myActiveDisplays.put(toAdd, myAllDisplays.get(toAdd));
		myScreen.addComponent(myAllDisplays.get(toAdd));
	}
	
	public void remove(String toRemove) {
		myActiveDisplays.remove(toRemove);
		myScreen.removeComponent(myAllDisplays.get(toRemove));
	}
	
	public Collection<String> getNames() {
		return myAllDisplays.keySet();
	}
	
	public boolean checkIfActive(String key) {
		return myActiveDisplays.containsKey(key);
	}
	
	private HashMap<String, UIDisplayComponent> makeComponents(String filePath) {
		HashMap<String, UIDisplayComponent> displays = new HashMap<String, UIDisplayComponent>();
		ResourceBundle bundle = ResourceBundle.getBundle(filePath);
		String comps = bundle.getString(BUNDLE_KEY);
		String[] allComps = comps.split(", ");
		for (String each: allComps) {
			UIDisplayComponent newDisplay = (UIDisplayComponent) Reflection.createInstance(COMPONENT_LOCATION+each+"Component", each);
			updateX(newDisplay, newDisplay.getSize().getWidth());
			updateY(newDisplay, newDisplay.getSize().getHeight());
			displays.put(each, newDisplay);
		}
		return displays;
	}
	
	private void copyMap(HashMap<String, UIDisplayComponent> toCopy) {
		for (String key: toCopy.keySet()) {
			add(key);
		}
	}
	
	private void setBounds(ReadOnlyDoubleProperty width, ReadOnlyDoubleProperty height) {
		myWidthBound = width;
		myHeightBound = height;
		myWidthBound.addListener(new ChangeListener() {
		      @Override
		      public void changed(ObservableValue o, Object oldVal, Object newVal) {
		        myAllDisplays.values().stream()
		        	.forEach(c -> updateX(c, (Double) newVal));
		      }
		    });
		myHeightBound.addListener(new ChangeListener() {
		      @Override
		      public void changed(ObservableValue o, Object oldVal, Object newVal) {
		    	  myAllDisplays.values().stream()
		        	.forEach(c -> updateY(c, (Double) newVal));
		      }
		    });
	}
	
	private void updateX(UIDisplayComponent width, Double newVal) {
		double x = width.getPos().x();
		if (x!= 0) {
			width.getDisplay().setTranslateX(newVal*x-width.getSize().getWidth());
		}
	}
	
	private void updateY(UIDisplayComponent height, Double newVal) {

		double x = height.getPos().y();
		if (x!= 0) {
			height.getDisplay().setTranslateY(newVal*x-height.getSize().getHeight());
		}
	}
}
