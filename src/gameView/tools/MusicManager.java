package gameView.tools;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class MusicManager {

	private ReadOnlyStringProperty myMusic;
	private Clip myClip;
	
	public MusicManager(ReadOnlyStringProperty music) {	
		myMusic = music;
		setBinding();
		startMusic();
	}
	
	public void stopMusic() {
		try {
			myClip.stop();
		} catch (Exception e) {	
			System.out.println("IN MUSIC MANAGER: NO CLIP");
		}
	}
	
	public void playMusic() {
		try {
			myClip.start();
		} catch (Exception e) {	
			System.out.println("IN MUSIC MANAGER: NO CLIP");
		}
	}
	
	private void setBinding() {
		myMusic.addListener(new ChangeListener<String>(){
	        public void changed(ObservableValue<? extends String> o, String oldVal, 
	                 String newVal){
	            startMusic();
	        }
	      });
	}
	
	private void startMusic() {
		try {
		    AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource(myMusic.get()));
		    myClip = AudioSystem.getClip();
		    myClip.open(audioInputStream2);
		    myClip.start();
		} catch(Exception e) {
			stopMusic();
		}
	}
	
}
