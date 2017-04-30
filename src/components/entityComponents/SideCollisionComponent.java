package components.entityComponents;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import actions.IAction;
import components.AComponent;
import components.IComponent;
import entity.IEntity;
import entity.IEntityManager;
import entity.restricted.IRestrictedEntity;
import gamedata.IRestrictedGameData;

public class SideCollisionComponent implements IComponent {
	private CollisionComponentType sideCollision;
	private Map<String, ArrayList<IAction>> typeActionMap;
	private Map<String, ArrayList<IAction>> labelActionMap;
	
	public SideCollisionComponent(CollisionComponentType sc, Map<String, ArrayList<IAction>> type, Map<String, ArrayList<IAction>> label){
		sideCollision=sc;
		typeActionMap = type;
		labelActionMap=label;
	}
	
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
	

	public IRestrictedGameData executeOnCollide(IEntity e,IEntity e2,IEntityManager myEM, IRestrictedGameData dg) {
		//maybe should refactor
		LabelComponent entityLabel = (LabelComponent) e.getComponent(ComponentType.Label);
		TypeComponent entityType = (TypeComponent) e.getComponent(ComponentType.Type);
		List<IEntity> newEntities = new ArrayList<IEntity>();
		if (entityLabel != null && labelActionMap.containsKey(entityLabel.getLabel())) {
			for (IAction action : labelActionMap.get(entityLabel.getLabel())) {
				dg = action.executeAction(e, e2,myEM, dg);
//				for (IRestrictedEntity re : dg.getRestrictedEntityManager().getRestrictedEntities()){
//					newEntities.add(re.clone());
//				}
				
			}
		}
		if (entityType!= null && typeActionMap.containsKey(entityType.getTypeString())) {
			for (IAction action : typeActionMap.get(entityType.getTypeString())) {

				dg= action.executeAction(e, e2,myEM, dg);
//				for (IRestrictedEntity re : dg.getRestrictedEntityManager().getRestrictedEntities()){
//					newEntities.add(re.clone());
//				}
			}
		}
		return dg;
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
		return new SideCollisionComponent(sideCollision, typeActionMap,labelActionMap);
	}
	
	public int hashCode(){
		return (getComponentType().toString() + sideCollision.toString()).hashCode();
	}
	
}