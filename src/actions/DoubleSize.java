package actions;

import java.util.ArrayList;
import java.util.List;

import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LocationComponent;
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
		
		return new ArrayList<IEntity>();
	}

}
