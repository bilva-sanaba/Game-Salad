package actions;

import java.util.List;

import class_annotations.BottomAction;
import class_annotations.LeftAction;
import class_annotations.RightAction;
import class_annotations.TopAction;
import components.entityComponents.ComponentType;
import components.entityComponents.SpriteComponent;
import entity.IEntity;
import entity.IEntityManager;
import exceptions.FileInputException;
import exceptions.InputException;
import exceptions.NotEnoughInputsException;
import gamedata.IRestrictedGameData;

@TopAction()
@LeftAction()
@BottomAction()
@RightAction()
public class Explosion  extends AbstractAction  implements IAction {
	
	public static final String EXPLOSION_IMAGE = "Feuer46.GIF";
	private String imgPath;
	
	public Explosion() {
		imgPath = EXPLOSION_IMAGE;
	}
	
	
	public Explosion(String imagePath) throws InputException {
		this();
		imgPath = super.validateFile(imagePath);
	}
	
	public Explosion(List<String> inputs) throws InputException {
		inputs = super.validateList(inputs, 1);
		imgPath = super.validateFile(inputs.get(0));
	}

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM, IRestrictedGameData currentGameData) {
//		ImagePropertiesComponent ic = (ImagePropertiesComponent) other.getComponent(ComponentType.ImageProperties);
		SpriteComponent otherSprite = (SpriteComponent) other.getComponent(ComponentType.Sprite);
		otherSprite.setString(imgPath);
		

		return getGameDataFactory().blankEntityData(currentGameData);
	}

}