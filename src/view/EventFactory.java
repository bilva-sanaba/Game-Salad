package view;

import java.lang.reflect.Constructor;

import view.toolbar.ToolBarButtonEvent;

/**
 * 
 * @author Jonathan
 *
 */
public class EventFactory {

	public ToolBarButtonEvent getEvent(String eventname, ViewData data) throws Exception {
		try {
			Class<?> evntObj = Class.forName(eventname); // get instruction class
			try {
				Constructor<?> eventObjCtor = evntObj.getConstructor(data.getClass());
				Object eventObject = eventObjCtor.newInstance(data); //create an instance of the class
				return (ToolBarButtonEvent) eventObject;
			} catch (Exception e) {
				throw new Exception();
			}
		} catch (ClassNotFoundException e) {
			throw new Exception();
		}
	}
}
