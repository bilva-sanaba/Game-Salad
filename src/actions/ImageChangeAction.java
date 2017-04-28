package actions;

import java.util.List;

import components.entityComponents.ComponentType;
import components.entityComponents.SpriteComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

public class ImageChangeAction  extends AbstractAction  implements IAction{
	private List<String> possibleImages;
	private int counter;
	public ImageChangeAction(List<String> strings){
		possibleImages = strings;
		counter=0;
	}
	@Override
	public IRestrictedGameData executeAction(IEntity player, IEntity npc, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		if (counter%10==0){
			SpriteComponent sc = (SpriteComponent) player.getComponent(ComponentType.Sprite);
			sc.setClassPath(possibleImages.get((counter/10)%possibleImages.size()));		
			player.changed(player);
		}
		counter++;
		return getGameDataFactory().blankEntityData(currentGameData);
	}
}
