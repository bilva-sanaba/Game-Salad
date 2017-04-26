package gamedata;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import components.entityComponents.LocationComponent;
import entity.EntityManager;
import entity.SplashEntity;
import entity.restricted.IRestrictedEntity;
import entity.restricted.IRestrictedEntityManager;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameData implements IGameData,IRestrictedGameData{
	
	private DoubleProperty points = new SimpleDoubleProperty();
	private DoubleProperty lives = new SimpleDoubleProperty(); 
	private IRestrictedEntityManager restrictedEntityManager; 
	private DoubleProperty level = new SimpleDoubleProperty(); 
	private LocationComponent mainPlayerLocation;

	private StringProperty music = new SimpleStringProperty();
	
	public GameData(double p, double l, IRestrictedEntityManager rem, double lvl, LocationComponent lc, String m){
		points.setValue(p);
		lives.setValue(l);
		restrictedEntityManager = rem;
		level.setValue(lvl);
		mainPlayerLocation = lc;
		music.setValue(m);
		
	}
	
	public DoubleProperty getPoints(){
		return points;
	}
	public DoubleProperty getLives(){
		return lives;
	}
	public IRestrictedEntityManager getRestrictedEntityManager(){
		return restrictedEntityManager;
	}
	public DoubleProperty getLevel(){
		return level; 
	}
	public LocationComponent getMainLocation(){
		return mainPlayerLocation;
	}
	public StringProperty getMusic(){
		return music;
	}
	
	public void setPoints(double d){
		points.setValue(d);
	}
	public void setLives(double d){
		lives.setValue(d);
	}
	public void setRestrictedEntityManager(IRestrictedEntityManager rem){
		restrictedEntityManager = rem;
	}
	public void setLevel(double d){
		level.setValue(d);
	}
	public void setMainLocation(LocationComponent lc){
		mainPlayerLocation = lc;
	}
	public void setMusic(String s){
		music.setValue(s);
	}

	@Override
	public GameData mergeWith(IRestrictedGameData merger) {
		IRestrictedEntityManager mergedEntityManager = this.mergeEntityManagers(merger);
		if (mergedEntityManager != null) {
			this.setRestrictedEntityManager(mergedEntityManager);
		}
		this.points = merger.getPoints();
		this.lives = merger.getLives();
		this.mainPlayerLocation = merger.getMainLocation();
		this.music = merger.getMusic();
		this.level = merger.getLives();
		return this;
	}
	
	
	
	/**
	 * Returns null if could not merge successfully.
	 * @param merger
	 * @return
	 */
	private IRestrictedEntityManager mergeEntityManagers(IRestrictedGameData merger) {
		Collection<IRestrictedEntity> selfNewEntities = this.getRestrictedEntityManager().getRestrictedEntities();
		Collection<IRestrictedEntity> mergerNewEntities = merger.getRestrictedEntityManager().getRestrictedEntities();
		for(IRestrictedEntity newEntity : mergerNewEntities) {
			if (!selfNewEntities.contains(newEntity)) {
				selfNewEntities.add(newEntity);
			}
		}
		IRestrictedEntityManager newEntityManager = createNewEntityManager(this.getRestrictedEntityManager(), selfNewEntities);
		return newEntityManager;
	}
	
	/**
	 * WARNING: RETURNS NULL IF IT COULD NOT CREATE SUCCESSFULLY.
	 * @param model
	 * @param newEntities
	 * @return
	 */
	private IRestrictedEntityManager createNewEntityManager(IRestrictedEntityManager model, Collection<IRestrictedEntity> newEntities) {
		try {
			Class<?> modelClass = Class.forName(model.getClass().getName());
			IRestrictedEntityManager newEntityManager = (IRestrictedEntityManager) modelClass.getConstructor(Collection.class).newInstance(newEntities);
			return newEntityManager;
		} catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			System.out.println("Need to handle exception line 93 of GameData.java");
		}
		return null;
	}
	
	public static void main(String[] args) {
		EntityManager em = new EntityManager();
		try {
			Class<?> clazz = Class.forName(em.getClass().getName());
			IRestrictedEntityManager irem = (IRestrictedEntityManager) clazz.getConstructor(Collection.class).newInstance(em.getRestrictedEntities());
			System.out.println(irem.getRestrictedEntities().size());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
