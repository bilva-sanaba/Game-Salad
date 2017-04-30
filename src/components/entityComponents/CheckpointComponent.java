package components.entityComponents;

import components.IComponent;
import entity.IEntityManager;

public class CheckpointComponent implements IComponent{
	
	private IEntityManager checkpointState;

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Checkpoint;
	}
	
	public void setState(IEntityManager currentState){
		checkpointState = currentState;
	}
	
	public IEntityManager getState(){
		return checkpointState;
	}

	@Override
	public IComponent newCopy() {
		CheckpointComponent myCheckpointComponent = new CheckpointComponent();
		if(checkpointState != null){
			myCheckpointComponent.setState(checkpointState);
		}
		return myCheckpointComponent;
	}

}
