package actions;

import java.util.ArrayList;
import java.util.List;

import components.entityComponents.ComponentType;
import components.entityComponents.SpriteComponent;
import entity.IEntity;
import entity.IEntityManager;

public class ChangeImage implements IAction {
	private String imagePath;
	public ChangeImage(String path){
		imagePath = path;
	}
	@Override
	public List<IEntity> executeAction(IEntity player, IEntity npc, IEntityManager myEM) {
		SpriteComponent x = (SpriteComponent) player.getComponent(ComponentType.Sprite);
		x.setClassPath(imagePath);
		return new ArrayList<IEntity>();
	}

}
