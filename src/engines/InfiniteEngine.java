package engines;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import engines.infinite.InfiniteEnum;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;

public class InfiniteEngine extends AbstractEngine implements IEngine{
	
	private InfiniteEnum infinite;
	private double difference;
	private LocationComponent mainPlayer;
	private List<IEntity> repeated;
	public InfiniteEngine(IEntityManager myEntityManager, InfiniteEnum infinite) {
		super(myEntityManager);
		this.infinite=infinite;
		this.repeated = new ArrayList<IEntity>();
		
		double maxHeight=0;
		double minHeight=0;
		
		for (IEntity e : myEntityManager.getEntities()){
			if (e.hasComponent(ComponentType.KeyInput)){
				mainPlayer = (LocationComponent) e.getComponent(ComponentType.Location);
			}
			LocationComponent lc = (LocationComponent) e.getComponent(ComponentType.Location);
			double pos =getDimensionalPosition(lc);
			maxHeight = Math.max(maxHeight, pos);
			minHeight = Math.min(minHeight, pos);
		}
		
		difference = maxHeight-minHeight;
	}
	
	private double getDimensionalPosition(LocationComponent lc){
		if (infinite==InfiniteEnum.Horizontal){
			return lc.getX();
		}else{
			if (infinite==InfiniteEnum.Vertical){
				return lc.getY();
			}
		}
		return 0;
	}
	
	@Override
	public IRestrictedGameData update(Collection<KeyCode> keysPressed, IRestrictedGameData gameData) {
		
		for (IEntity e : getEManager().getEntities().toArray(new IEntity[getEManager().getEntities().size()])){
			
			if (infinite==InfiniteEnum.Horizontal){
				LocationComponent lc = (LocationComponent) e.getComponent(ComponentType.Location);
				if (mainPlayer.getX()-lc.getX()>difference/2 && !repeated.contains(e)){
					repeated.add(e);
					IEntity newEntity = e.newCopy(getEManager().getEntities().size());
					newEntity.addComponent(new LocationComponent(lc.getX()+difference,lc.getY()));
					getEManager().getEntities().add(newEntity);
					getEManager().changed(newEntity);
					getEManager().getEntities().remove(e);
					e.changed(null);
				}
				
			}
			else if (infinite==InfiniteEnum.Vertical){
				LocationComponent lc = (LocationComponent) e.getComponent(ComponentType.Location);
				if (mainPlayer.getY()-lc.getY()>difference/2 && !repeated.contains(e)){
					repeated.add(e);
					IEntity newEntity = e.newCopy(getEManager().getEntities().size());
					newEntity.addComponent(new LocationComponent(lc.getX(),lc.getY()+difference));
					getEManager().getEntities().add(newEntity);
					getEManager().changed(newEntity);
				}
				
			}
		}
		return gameData;
	}

}
