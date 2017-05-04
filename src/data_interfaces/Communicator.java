package data_interfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.w3c.dom.Element;

import actions.PointsAction;
import actions.Reload;
import actions.RemoveAction;
import actions.ShootAction;
import actions.SmartJump;
import actions.SmartShoot;
import actions.Teleport;
import components.entityComponents.AccelerationComponent;
import components.entityComponents.CheckCollisionComponent;
import components.entityComponents.CollidableComponent;
import components.entityComponents.CollisionComponentType;
import components.entityComponents.CollisionComponentsHandler;
import components.entityComponents.ComponentType;
import components.entityComponents.ControllableComponent;
import components.entityComponents.DamagedComponent;
import components.entityComponents.EntityType;
import components.entityComponents.GoalComponent;
import components.entityComponents.HealthComponent;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.KeyInputComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.MonsterType;
import components.entityComponents.MonsterTypeComponent;
import components.entityComponents.ObjectCreationComponent;
import components.entityComponents.OrientationComponent;
import components.entityComponents.SideCollisionComponent;
import components.entityComponents.SpriteComponent;
import components.entityComponents.StepComponent;
import components.entityComponents.StrengthComponent;
import components.entityComponents.TerminalVelocityComponent;
import components.entityComponents.TimeComponent;
import components.entityComponents.TypeComponent;
import components.entityComponents.VelocityComponent;
import components.keyExpressions.JumpAction;
import components.keyExpressions.LeftAction;
import components.keyExpressions.RightAction;
import controller.Camera;
import engines.AIEngine;
import engines.CollisionEngine;
import engines.InfiniteEngine;
import engines.InputEngine;
import engines.MovementEngine;
import engines.TimeEngine;
import engines.infinite.InfiniteEnum;
import entity.*;
import entity.presets.AbstractBlock;
import entity.presets.AbstractBreakableBox;
import entity.presets.AbstractEnemy;
import entity.presets.AbstractGoal;
import entity.presets.AbstractMysteryBlock;
import entity.presets.AbstractPlayer;
import entity.presets.AbstractPowerup;
import entity.presets.DeathBlock;
import entity.presets.DoodleJumpPlatform;
import entity.restricted.IRestrictedEntityManager;
import gamedata.GameData;
import javafx.scene.input.KeyCode;

public class Communicator extends GameSavingDataTool implements EngineCommunication, PlayerCommunication {

	private String fileName;
	List<Map> results;

	public Communicator(String s) {
		fileName = s;
		XMLPlacedParser xp = new XMLPlacedParser();
		results = xp.getData(fileName);
	}
	public List<IEntityManager> getIEntityManagers() {
		Map <Integer, Map<Integer, Entity>> m = results.get(getEntityOrder());
		List <IEntityManager> ret = new ArrayList<IEntityManager>();
		List <IEntity> toBeAdded;


		for (int i = 1; i <= m.size(); i++) {
			toBeAdded = new ArrayList<IEntity>();
			for (Integer j: m.get(i).keySet()) {
				toBeAdded.add(m.get(i).get(j));
			}
			ret.add(new EntityManager(toBeAdded));

		}
		return ret;
	}
	



	@Override
	public List<LevelEntity> getLevelEntities() {
		Map <Integer, LevelEntity> m = results.get(getLevelOrder());
		List<LevelEntity> ret = new ArrayList<LevelEntity>();
		for (int i = 1; i <= m.size(); i++) {
			ret.add(m.get(i));
		}
		return ret;
	}



	@Override
	public SplashData getSplashEntity() {
		return (SplashData) results.get(getSplashOrder()).get(getSplashConstant());
	}



	@Override
	public InfiniteEnum getInfinite() {
		Map<Integer, LevelEntity> m = results.get(getLevelOrder());
		return m.get(getStorageLevel()).getInfiniteEnum();
	}


	public String getMusic() {
		Map<Integer, LevelEntity> m = results.get(getLevelOrder());
		return m.get(getStorageLevel()).getMusic();
	}

	public int getLives () {
		Map <Integer, LevelEntity> m = results.get(getLevelOrder());
		return m.get(getStorageLevel()).getLives();
	}
	@Override
	public boolean getCameraOn() {
		Map<Integer, LevelEntity> m = results.get(getLevelOrder());
		return m.get(getStorageLevel()).getCamera();
	}
}