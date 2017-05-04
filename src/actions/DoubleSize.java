package actions;

import java.util.List;

import class_annotations.BottomAction;
import class_annotations.LeftAction;
import class_annotations.RightAction;
import class_annotations.TopAction;
import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LocationComponent;
import entity.IEntity;
import entity.IEntityManager;
import exceptions.NotEnoughInputsException;
import gamedata.IRestrictedGameData;


@TopAction()
@LeftAction()
@BottomAction()
@RightAction()
public class DoubleSize  extends AbstractAction implements IAction {
	private boolean c;
	
	public DoubleSize(boolean correction){
		c = correction;
	}
	
	public DoubleSize(List<String> inputs) throws NotEnoughInputsException {
		inputs = super.validateList(inputs, 1);
		System.out.println("here!!  " + inputs.get(0).toLowerCase().equals("true"));
		c = inputs.get(0).toLowerCase().equals("true");
		
		
	}
	
	
	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM, IRestrictedGameData currentGameData) {
//		SpriteComponent x = (SpriteComponent) other.getComponent(ComponentType.Sprite);
//		x.setClassPath(imagePath);
		ImagePropertiesComponent y = (ImagePropertiesComponent) other.getComponent(ComponentType.ImageProperties);
		if (c){
			LocationComponent t = (LocationComponent) other.getComponent(ComponentType.Location);
//		VelocityComponent v = (VelocityComponent) other.getComponent(ComponentType.Velocity);
			t.setY(t.getY()-y.getHeight());
		}
		y.setHeight(y.getHeight()*2);
		y.setWidth(y.getWidth()*2);
		other.changed(other);

		return getGameDataFactory().blankEntityData(currentGameData);
	}

}
