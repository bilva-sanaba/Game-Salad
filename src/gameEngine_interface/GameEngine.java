package gameEngine_interface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import controller.Camera;
import controller.WorldAnimator;
import data_interfaces.EngineCommunication;
import engines.AIEngine;
import engines.AbstractEngine;
import engines.CollisionEngine;
import engines.IEngine;
import engines.InfiniteEngine;
import engines.InputEngine;
import engines.MovementEngine;
import engines.TimeEngine;
import entity.GPEntityManager;
import entity.IEntity;
import entity.IEntityManager;
import entity.SplashData;
import entity.restricted.IRestrictedEntityManager;
import gameEngine.chooser.GameEngineChooser;
import gameEngine.chooser.IGameEngineChooser;
import gameView.userInput.IRestrictedUserInputData;
import gamedata.GameData;
import gamedata.GameDataFactory;
import gamedata.IGameData;
import gamedata.IRestrictedGameData;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.input.KeyCode;
/**
 * Basic GameEngine class Note: the engines must be created in someway, likely
 * via reflection
 * 
 * @author Bilva
 *
 */
public class GameEngine implements GameEngineInterface {
	private IEntityManager myEntityManager;
	private List<IEngine> myEngines;
	private IGameData myGameData;
	private IGameEngineChooser myGameEngineChooser;
	private int numUpdates;
	private boolean sliderPause;
	private List<IEntityManager> myEntityManagers;
	private List<IEntityManager> previousEntityManagers;
	private EntityLoader myEntityLoader;
	public static final int SAVE_FREQUENCY = WorldAnimator.FRAMES_PER_SECOND;
	
	public GameEngine(IRestrictedUserInputData data){
		initializeUserData(data);
		previousEntityManagers = new ArrayList<IEntityManager>();
		sliderPause = false;
	}
	private void initializeUserData(IRestrictedUserInputData data){
		ReadOnlyDoubleProperty p = data.getRewind();
		p.addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
            	if (old_val.doubleValue()!=new_val.doubleValue()){
                    rewindState(old_val.doubleValue(),new_val.doubleValue());   
            	}

            }
        });
	}
	
	public IRestrictedGameData loadData(EngineCommunication c){	
		//DUMMYLOAD
//		myEntityManagers = dummyLoad();
//		myEntityManager = myEntityManagers.get(0);
		
		//REAL USE THIS
		myEntityManagers = c.getIEntityManagers();
		myEntityManager = myEntityManagers.get(0);
		myGameEngineChooser = new GameEngineChooser(myEntityManager, c.getInfinite());
		myEngines = myGameEngineChooser.getEngines();
		myEntityLoader = new EntityLoader(myEntityManager);
		
		LocationComponent lc = (LocationComponent) getMainCharacter().getComponent(ComponentType.Location);
		List<String> listl = new ArrayList<String>();

		myGameData = new GameData(0,0,(IRestrictedEntityManager) myEntityManager, 0, lc,listl,"");
		return (IRestrictedGameData) myGameData;
	}
	
	public List<IEntityManager> save(){
		List<IEntityManager> mySaveState = new ArrayList<IEntityManager>();
		for (IEntityManager em : myEntityManagers){
			mySaveState.add(em.copy());
		}
		return mySaveState;
	}

	/**
	 * Runs each Engine in my Engine
	 */
	@Override
	public void handleUpdates(Collection<KeyCode> keysPressed) {
		resetStoredStates();
		saveNewEntityManager();
		for (IEngine s : myEngines){
			IRestrictedGameData rgd = s.update(keysPressed, (IRestrictedGameData) myGameData);
			updateLevel(rgd);
			GameDataFactory gameDataFactory = new GameDataFactory();
			gameDataFactory.updateGameData(myGameData,rgd);		
		}
	}
	private void updateLevel(IRestrictedGameData restrictedGameData){
		if (myGameData.getLevel().intValue()!=restrictedGameData.getLevel().intValue()){
			myEntityLoader.loadNew(myEntityManagers.get(restrictedGameData.getLevel().intValue()));
		}
	}
	private void resetStoredStates(){
		if (sliderPause==true){
			sliderPause=false;
			previousEntityManagers.clear();
		}
	}
	
	private IEntity getMainCharacter(){
		for(IEntity e : myEntityManager.getEntities()){
			if(e.hasComponent(ComponentType.KeyInput)){
				return e;
			}
		}
		return null;
	}
	
//	public void addCamera(Camera c) {
//		myEntityManager.changed(c);
//	}
	
	private void rewindState(Double old, Double next){
		sliderPause=true;
		Integer previousIndex = (Double.valueOf(previousEntityManagers.size()*old)).intValue();
		Integer index = (Double.valueOf(previousEntityManagers.size()*next)).intValue();
		if (next==1){index--;};
		if (previousIndex!=index){
			myEntityLoader.loadNew(previousEntityManagers.get(index));
		}
	}
	private void saveNewEntityManager() {
		numUpdates++;
		if (numUpdates % SAVE_FREQUENCY*10 == 0) {
			previousEntityManagers.add((myEntityManager.copy()));
			
		}
		while (previousEntityManagers.size()>25) {
			previousEntityManagers.remove(0);
		}
	}
	
}