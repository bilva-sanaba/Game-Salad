package actions;

import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import components.LocationComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.SpriteComponent;
import entity.IEntity;
import entity.IEntityManager;

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
		t.setY(t.getY()-y.getHeight());
		}
		y.setHeight(y.getHeight()*2);
		y.setWidth(y.getWidth()*2);
		player.changed(player);
		try{
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("Obi-Wan - Hello there..wav"));
		    Clip clip = AudioSystem.getClip();
		    clip.open(audioInputStream);
		    clip.start();
		    AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("badboujee.wav"));
		    Clip clip2 = AudioSystem.getClip();
		    if (!clip.isActive()) {
		    	clip2.open(audioInputStream2);
			    clip2.start();
		    }
		    
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	    
		
		return new ArrayList<IEntity>();
	}

}
