package view;

import voogasalad.util.reflection.*;
import view.commands.RightClickEvent;
import view.toolbar.ToolBarButtonEvent;

/**
 * 
 * @author Jonathan
 * @author Justin
 * @author Jack
 *
 */
public class EventFactory {
	private static final String PREFIX = "view.toolbar.";
	private static final String RIGHTCLICKPREFIX = "view.commands.";

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
	
	public RightClickEvent getRightClickEvent(String eventname, ViewData data) {	
		RightClickEvent reflectedEvent;
		System.out.println(RIGHTCLICKPREFIX + eventname);
		try {
			reflectedEvent = (RightClickEvent) Reflection.createInstance(RIGHTCLICKPREFIX + eventname, data);
		} catch (Exception e) {
			throw new ReflectionException(ReflectionException.EVENT_REFLECTION_ERROR);
		}
		return reflectedEvent;
	}
}
