package engines.infinite;

import java.util.Collection;

import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;

public class InfiniteHorizontal implements IInfiniteAlgorithm{
	
	@Override
	public String getLabelName() {
		// TODO Auto-generated method stub
		return "Horizontal";
	}
	@Override
	public void update(Collection<KeyCode> keysPressed, IRestrictedGameData gameData, IEntity myEntity, IEntityManager myEntityManager) {
		LocationComponent lc = (LocationComponent) e.getComponent(ComponentType.Location);
		if (mainPlayer.getX()-lc.getX()>difference/2 && !repeated.contains(e)){
			repeated.add(e);
			IEntity newEntity = e.newCopy(getEManager().getEntities().size());
			newEntity.addComponent(new LocationComponent(lc.getX()+difference,lc.getY()));
			getEManager().getEntities().add(newEntity);
			getEManager().changed(newEntity);
		}
		
	}
}
