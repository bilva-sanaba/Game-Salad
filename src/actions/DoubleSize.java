package actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.SpriteComponent;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class DoubleSize implements IAction {
	private boolean c;
	
	public DoubleSize(boolean correction){
		c = correction;
	}
	@Override
	public List<IEntity> executeAction(IEntity player, IEntity npc, IEntityManager myEM) {
		SpriteComponent x = (SpriteComponent) player.getComponent(ComponentType.Sprite);
		
//		x.setClassPath(imagePath);
		ImagePropertiesComponent y = (ImagePropertiesComponent) player.getComponent(ComponentType.ImageProperties);
		if (c){
		LocationComponent t = (LocationComponent) player.getComponent(ComponentType.Location);
		VelocityComponent v = (VelocityComponent) player.getComponent(ComponentType.Velocity);
		t.setY(t.getY()-y.getHeight());
		}
		y.setHeight(y.getHeight()*2);
		y.setWidth(y.getWidth()*2);
		player.changed(player);
		try{

//		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("Obi-Wan - Hello there..wav"));
//		    Clip clip = AudioSystem.getClip();
//		    clip.open(audioInputStream);
//		    clip.start();
//		    AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("badboujee.wav"));
//		    Clip clip2 = AudioSystem.getClip();
//		    if (!clip.isActive()) {
//		    	clip2.open(audioInputStream2);
//			    clip2.start();
//		    }
		
			/**
			 * Simpler audio version for testing speed @Hamsa
			 */
//			AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("badboujee.wav"));
//		    Clip clip2 = AudioSystem.getClip();
//		    	clip2.open(audioInputStream2);
//			    clip2.start();	    
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	    

		return new ArrayList<IEntity>();
	}

}
