package components.entityComponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import actions.IAction;
import components.IComponent;

public class TimeComponent implements IComponent {
	private Map<IAction, Integer> timeActions;
	private Map<IAction,Integer>  lastTimeAction;
	public TimeComponent(){
		timeActions = new HashMap<IAction, Integer>();
		lastTimeAction = new HashMap<IAction,Integer>();
		
	}
	public TimeComponent(Map<IAction,Integer> timeActions2) {
		timeActions= timeActions2;
		lastTimeAction = new HashMap<IAction,Integer>();
	}
	public TimeComponent(IAction action, Integer delay){
		timeActions = new HashMap<IAction, Integer>();
		lastTimeAction = new HashMap<IAction,Integer>();
		addAction(action, delay);
	}
	
	public void addAction(IAction action,Integer delay){
		if (!timeActions.containsKey(action)){
		
		timeActions.put(action,delay);

		}
	}
	public Map<IAction,Integer> getLastTime(){
		return lastTimeAction;
	}
	public Map<IAction,Integer> getConstantTime(){
		return timeActions;
	}

	@Override
	public ComponentType getComponentType() {
		// TODO Auto-generated method stub
		return ComponentType.Time;
	}

	@Override
	public IComponent newCopy() {
		// TODO Auto-generated method stub
		return new TimeComponent(timeActions);
	}

}
