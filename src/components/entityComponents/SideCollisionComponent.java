package components.entityComponents;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import actions.IAction;
import components.IComponent;
import entity.IEntity;
import entity.IEntityManager;

public class SideCollisionComponent implements IComponent {
	private CollisionComponentType sideCollision;
	private Map<String, ArrayList<IAction>> typeActionMap;
	private Map<String, ArrayList<IAction>> labelActionMap;
	
	public SideCollisionComponent(CollisionComponentType sideOfCollision) {
		sideCollision = sideOfCollision;
		
		typeActionMap = new HashMap<String, ArrayList<IAction>>();
		labelActionMap = new HashMap<String, ArrayList<IAction>>();
	}
	
	public void addActionForLabel(LabelComponent label, IAction action) {
		if(!labelActionMap.containsKey(label.getLabel())) {
			labelActionMap.put(label.getLabel(), new ArrayList<IAction>());
		}
		ArrayList<IAction> actions = labelActionMap.get(label.getLabel());
		actions.add(action);
		labelActionMap.put(label.getLabel(), actions);
	}
	
	public void addActionForType(TypeComponent type, IAction action) {
		if(!typeActionMap.containsKey(type.getTypeString())) {
			typeActionMap.put(type.getTypeString(), new ArrayList<IAction>());
		}
		ArrayList<IAction> actions = typeActionMap.get(type.getTypeString());
		actions.add(action);
		typeActionMap.put(type.getTypeString(), actions);
	}
	
	
	
	public List<IEntity> executeOnCollide(IEntity e,IEntity e2,IEntityManager myEM) {
		//maybe should refactor
		LabelComponent entityLabel = (LabelComponent) e.getComponent(ComponentType.Label);
		TypeComponent entityType = (TypeComponent) e.getComponent(ComponentType.Type);
		List<IEntity> newEntities = new ArrayList<IEntity>();
		if (labelActionMap.containsKey(entityLabel.getLabel())) {
			for (IAction action : labelActionMap.get(entityLabel.getLabel())) {
				List<IEntity> actionCreatedEntities = action.executeAction(e, e2,myEM);
				newEntities.addAll(actionCreatedEntities);
			}
			return newEntities;
		}
		if (typeActionMap.containsKey(entityType.getTypeString())) {
			for (IAction action : typeActionMap.get(entityType.getTypeString())) {
				action.executeAction(e,e2,myEM);
				List<IEntity> actionCreatedEntities = action.executeAction(e,e2,myEM);
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