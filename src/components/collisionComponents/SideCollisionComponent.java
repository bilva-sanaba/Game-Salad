package components.collisionComponents;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import actions.IAction;
import components.entityComponents.ComponentType;
import components.entityComponents.LabelComponent;
import components.entityComponents.TypeComponent;
import components.IComponent;
import entity.Entity;
import entity.IEntity;

public class SideCollisionComponent implements IComponent {
	private CollisionComponentType sideCollision;
	private Map<TypeComponent, ArrayList<IAction>> typeActionMap;
	private Map<String, ArrayList<IAction>> labelActionMap;
	
	public SideCollisionComponent(CollisionComponentType sideOfCollision) {
		sideCollision = sideOfCollision;
		
		typeActionMap = new HashMap<TypeComponent, ArrayList<IAction>>();
		labelActionMap = new HashMap<String, ArrayList<IAction>>();
	}
	
	public void addActionForLabel(LabelComponent label, IAction action) {
		if(!labelActionMap.containsKey(label.getLabel())) {
			labelActionMap.put(label.getLabel(), new ArrayList<IAction>());
		}
		ArrayList<IAction> actions = labelActionMap.get(label.getLabel());
		actions.add(action);
		labelActionMap.put(label.getLabel(), actions);
		System.out.println(label.getLabel() + " " + action.getClass().getSimpleName());
	}
	
	public void addActionForType(TypeComponent type, IAction action) {
		if(!typeActionMap.containsKey(type)) {
			typeActionMap.put(type, new ArrayList<IAction>());
		}
		ArrayList<IAction> actions = typeActionMap.get(type);
		actions.add(action);
		typeActionMap.put(type, actions);
	}
	
	
	
	public List<IEntity> executeOnCollide(IEntity e) {
		//maybe should refactor
		LabelComponent entityLabel = (LabelComponent) e.getComponent(ComponentType.Label);
		TypeComponent entityType = (TypeComponent) e.getComponent(ComponentType.Type);
		List<IEntity> newEntities = new ArrayList<IEntity>();
		System.out.println(entityLabel.getLabel() + " is label");

		if (labelActionMap.containsKey(entityLabel.getLabel())) {
			System.out.println("label was found");
			for (IAction action : labelActionMap.get(entityLabel.getLabel())) {
				List<IEntity> actionCreatedEntities = action.executeAction(e);
				newEntities.addAll(actionCreatedEntities);
			}
			return newEntities;
		}
		if (typeActionMap.containsKey(entityType)) {
			for (IAction action : typeActionMap.get(entityType)) {
				action.executeAction(e);
				List<IEntity> actionCreatedEntities = action.executeAction(e);
				newEntities.addAll(actionCreatedEntities);
			}
		}
		return newEntities;
	}
	
	public CollisionComponentType whichSide() {
		return sideCollision;
	}
	@Override
	public ComponentType getComponentType() {
		// TODO Auto-generated method stub
		return ComponentType.CollisionSide;
	}
	@Override
	public IComponent newCopy() {
		// TODO Auto-generated method stub
		return null;
	}
	
}