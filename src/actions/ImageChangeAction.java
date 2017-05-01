package actions;

import java.util.List;
import java.util.Map;
import java.util.Queue;

import components.entityComponents.ComponentType;
import components.entityComponents.SpriteComponent;
import entity.IEntity;
import entity.IEntityManager;
import exceptions.InputException;
import exceptions.NotEnoughInputsException;
import gamedata.IRestrictedGameData;

public class ImageChangeAction  extends AbstractAction  implements IAction{
	private List<String> possibleImages;
	private int counter;
	
	
	public ImageChangeAction(Map<Integer, String> inputs) {
		for (Integer key : inputs.keySet()) {
			possibleImages.add(inputs.get(key));
		}
	}
	
	public ImageChangeAction(List<String> inputs) throws InputException{
		possibleImages = super.validateList(inputs, inputs.size());
		for (String s : inputs) {
			s = super.validateFile(s); //just validates each string and throws exception otherwise
		}
		counter=0;
	}
	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		if (counter%10==0){
			SpriteComponent sc = (SpriteComponent) other.getComponent(ComponentType.Sprite);
			sc.setClassPath(possibleImages.get((counter/10)%possibleImages.size()));		
			other.changed(other);
		}
		counter++;
		return getGameDataFactory().blankEntityData(currentGameData);
	}
}