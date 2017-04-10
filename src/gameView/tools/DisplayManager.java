package gameView.tools;

import gameView.UIDisplayComponent;
import gameView.gameScreen.GameScreen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class DisplayManager {

	private HashMap<String, UIDisplayComponent> myAllDisplays;
	private HashMap<String, UIDisplayComponent> myActiveDisplays;
	private GameScreen myScreen;
	
	public DisplayManager(GameScreen screen) {
		myScreen = screen;
		myAllDisplays = makeComponents();
		myActiveDisplays = makeComponents();
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
	
	private HashMap<String, UIDisplayComponent> makeComponents() {
		HashMap<String, UIDisplayComponent> displays = new HashMap<String, UIDisplayComponent>();
		displays.put("MA;SDLF", null);
		displays.put("ALSDJF", null);
		displays.put(";LIJA;SDLF", null);
		displays.put("MA;;LASJDF;LAK", null);
		displays.put("A.DKFJAL;JOIJW", null);
		displays.put(";A;LSKDJFA;LSDKJFA;SDLF", null);
		return displays;
	}
}
