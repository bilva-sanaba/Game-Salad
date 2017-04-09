package view;

import java.lang.reflect.Constructor;
import java.util.*;

import groovyjarjarantlr.collections.List;
import voogasalad.util.reflection.*;

import view.toolbar.ToolBarButtonEvent;

/**
 * 
 * @author Jonathan
 *
 */
public class EventFactory {
	
	private static final String PREFIX = "view.toolbar.";

	public ToolBarButtonEvent getEvent(String eventname, ViewData data) throws Exception {
		Reflection reflector = new Reflection();
		
		return (ToolBarButtonEvent)reflector.createInstance(PREFIX + eventname, data);
	}
}
