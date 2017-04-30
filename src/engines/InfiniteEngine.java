package engines;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import data_interfaces.InfiniteEnum;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;

public class InfiniteEngine extends AbstractEngine {
	private InfiniteEnum infinite;
	private double difference;
	private LocationComponent mainPlayer;
	private List<IEntity> repeated = new ArrayList<IEntity>();
	public InfiniteEngine(IEntityManager myEntityManager, InfiniteEnum infinite) {
		super(myEntityManager);
		this.infinite=infinite;
		double maxHeight=0;
		double minHeight=0;
		for (IEntity e : myEntityManager.getEntities()){
			if (e.getComponent(ComponentType.KeyInput)!=null){
				mainPlayer = (LocationComponent) e.getComponent(ComponentType.Location);
			}
			LocationComponent lc = (LocationComponent) e.getComponent(ComponentType.Location);
			if (infinite==InfiniteEnum.Horizontal){
				double X = lc.getX();
				maxHeight = Math.max(maxHeight, X);
				minHeight = Math.min(minHeight, X);
			}else{
				if (infinite==InfiniteEnum.Vertical){
					double Y = lc.getY();
					maxHeight = Math.max(maxHeight, Y);
					minHeight = Math.min(minHeight, Y);
				}
			}
		}
		difference = maxHeight-minHeight;
	}

	@Override
	public IRestrictedGameData update(Collection<KeyCode> keysPressed, IRestrictedGameData gameData) {
		
		for (IEntity e : getEManager().getEntities().toArray(new IEntity[getEManager().getEntities().size()])){
			if (infinite==InfiniteEnum.Horizontal){
				LocationComponent lc = (LocationComponent) e.getComponent(ComponentType.Location);
				if (mainPlayer.getX()-lc.getX()>difference/2 && !repeated.contains(e)){
					repeated.add(e);
					IEntity newEntity = e.newCopy();
					newEntity.addComponent(new LocationComponent(lc.getX()+difference,lc.getY()));
					getEManager().getEntities().add(newEntity);
					getEManager().changed(newEntity);
				}
				
			}
			else if (infinite==InfiniteEnum.Vertical){
				LocationComponent lc = (LocationComponent) e.getComponent(ComponentType.Location);
				if (mainPlayer.getY()-lc.getY()>difference/2 && !repeated.contains(e)){
					repeated.add(e);
					IEntity newEntity = e.newCopy();
					newEntity.addComponent(new LocationComponent(lc.getX(),lc.getY()+difference));
					getEManager().getEntities().add(newEntity);
					getEManager().changed(newEntity);
				}
				
			}
		}
		return gameData;
	}

}
