package components.entityComponents;

import components.IComponent;
import entity.IEntityManager;
import gamedata.GameDataFactory;
import gamedata.IGameData;
import gamedata.IRestrictedGameData;

public class CheckpointComponent implements IComponent{
	
	private IEntityManager checkpointState;
	private IGameData checkPointData;
	@Override
	public ComponentType getComponentType() {
		return ComponentType.Checkpoint;
	}
	
	public void setState(IEntityManager currentState, IRestrictedGameData currentData){
		checkpointState = currentState.copy();
		GameDataFactory gdf = new GameDataFactory();
		checkPointData = gdf.blankEntityData(currentData);
		
		
	}
	
	public IEntityManager getState(){
		return checkpointState;
	}
	public IRestrictedGameData getData(){
		return checkPointData;
	}

	@Override
	public IComponent newCopy() {
		CheckpointComponent myCheckpointComponent = new CheckpointComponent();
		if(checkpointState != null){
			myCheckpointComponent.setState(checkpointState,checkPointData);
		}
		return myCheckpointComponent;
	}

}
