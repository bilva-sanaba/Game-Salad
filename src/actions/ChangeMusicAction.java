package actions;

import java.util.List;

import class_annotations.*;
import entity.IEntity;
import entity.IEntityManager;
import exceptions.FileInputException;
import gamedata.GameData;
import gamedata.IRestrictedGameData;


@TopAction()
@LeftAction()
@BottomAction()
@RightAction()
public class ChangeMusicAction extends AbstractAction implements IAction {
	private String newSong;
	public ChangeMusicAction(String song) throws FileInputException{
		
		newSong= super.validateFile(song);
	}
	
	public ChangeMusicAction(List<String> reflectionInputs) throws Exception {
		reflectionInputs = super.validateList(reflectionInputs, 1);
		newSong = reflectionInputs.get(0);
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
