package actions;

import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import alerts.VoogaError;
import entity.IEntity;
import entity.IEntityManager;
import exceptions.FileInputException;
import exceptions.InputException;
import exceptions.NotEnoughInputsException;
import gamedata.GameData;
import gamedata.IRestrictedGameData;

public class MusicPlayAction extends AbstractAction implements IAction {
	private String playedSong;
	
	public MusicPlayAction(String mySong){
		playedSong = mySong;
	}
	
	public MusicPlayAction(List<String> inputs) throws InputException {
		inputs = super.validateList(inputs, 1);
		playedSong = super.validateFile(inputs.get(0));
	}
	
	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		try{
//		    AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource(playedSong));
//		    Clip clip2 = AudioSystem.getClip();
//		    clip2.open(audioInputStream2);
//		    clip2.start();
		}
		catch(Exception ex)
		{
			new VoogaError("File Not Found", "Music Could Not Be Played");
		}

		GameData r =getGameDataFactory().blankEntityData(currentGameData);
//		r.setMusic(playedSong);
		try {
			return new ChangeMusicAction(playedSong).executeAction(other, self, myEM, currentGameData);
		} catch (FileInputException e) {
			
			return r;
		}
	}

}
