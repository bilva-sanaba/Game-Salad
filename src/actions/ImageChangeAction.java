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
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		SpriteComponent sc = (SpriteComponent) other.getComponent(ComponentType.Sprite);
		sc.setClassPath(possibleImages.get(counter));
		counter = (counter+1)%possibleImages.size();
		return getGameDataFactory().blankEntityData(currentGameData);
		
	}

}
