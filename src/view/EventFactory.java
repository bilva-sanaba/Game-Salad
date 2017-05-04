package view;

import voogasalad.util.reflection.*;
import entity.Entity;
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

	private UtilityFactory myUtilF;

	public EventFactory(UtilityFactory utilF) {
		myUtilF = utilF;
	}

	public ToolBarButtonEvent getEvent(String eventname, ViewData data) {
		ToolBarButtonEvent reflectedEvent;
		try {
			reflectedEvent = (ToolBarButtonEvent) Reflection.createInstance(PREFIX + eventname, myUtilF, data);
		} catch (Exception e) {
			throw new ReflectionException(ReflectionException.EVENT_REFLECTION_ERROR);
		}
		return reflectedEvent;
	}

	public RightClickEvent getRightClickEvent(String eventname, UtilityFactory util, ViewData data, Entity entity,
			double x, double y) {
		RightClickEvent reflectedEvent;
		try {
			if (eventname.equals("EditCommand")) {
				reflectedEvent = (RightClickEvent) Reflection.createInstance(RIGHTCLICKPREFIX + eventname, util, data,
						entity, x, y);
			} else {
				System.out.println(RIGHTCLICKPREFIX + eventname);
				reflectedEvent = (RightClickEvent) Reflection.createInstance(RIGHTCLICKPREFIX + eventname, data, entity,
						x, y);
			}
		} catch (Exception e) {
			throw new ReflectionException(ReflectionException.EVENT_REFLECTION_ERROR);
		}
		return reflectedEvent;
	}
}
