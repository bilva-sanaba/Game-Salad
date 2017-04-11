package view;

import voogasalad.util.reflection.*;
import view.toolbar.ToolBarButtonEvent;

/**
 * 
 * @author Jonathan
 * @author Justin
 *
 */
public class EventFactory {
	private static final String PREFIX = "view.toolbar.";

	public ToolBarButtonEvent getEvent(String eventname, ViewData data) {	
		ToolBarButtonEvent reflectedEvent;
		System.out.println(PREFIX + eventname);
		try {
			reflectedEvent = (ToolBarButtonEvent) Reflection.createInstance(PREFIX + eventname, data);
		} catch (Exception e) {
			throw new ReflectionException(ReflectionException.EVENT_REFLECTION_ERROR);
		}
		return reflectedEvent;
	}
}
