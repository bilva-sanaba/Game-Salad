package actions;

import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameData;
import gamedata.IRestrictedGameData;

public class ChangeMusicAction extends AbstractAction implements IAction {
	private String newSong;
	public ChangeMusicAction(String song){
		newSong= song;
	}
	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		GameData gd = getGameDataFactory().blankEntityData(currentGameData);

		gd.setMusic(newSong);
		// TODO Auto-generated method stub
		return gd;
	}

}
