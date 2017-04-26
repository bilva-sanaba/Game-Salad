package actions;

import gamedata.GameDataFactory;

public abstract class AbstractAction {
	private GameDataFactory gdf;
	public AbstractAction(){
		gdf = new GameDataFactory();
	}
	protected GameDataFactory getGameDataFactory(){
		return gdf;
	}
}
