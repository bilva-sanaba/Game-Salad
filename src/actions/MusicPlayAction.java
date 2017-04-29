package actions;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import alerts.VoogaError;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameData;
import gamedata.IRestrictedGameData;

public class MusicPlayAction extends AbstractAction implements IAction {
	private String playedSong;
	public MusicPlayAction(String mySong){
		playedSong = mySong;
	}
	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		try{
		    AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource(playedSong));
		    Clip clip2 = AudioSystem.getClip();
		    clip2.open(audioInputStream2);
		    clip2.start();
		}
		catch(Exception ex)
		{
			new VoogaError("File Not Found", "Music Could Not Be Played");
		}
		GameData r =getGameDataFactory().blankEntityData(currentGameData);
//		r.setMusic(playedSong);
		return r;
	}

}
