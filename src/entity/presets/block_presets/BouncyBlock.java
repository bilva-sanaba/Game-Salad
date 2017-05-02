package entity.presets.block_presets;

import components.entityComponents.CheckCollisionComponent;
import components.entityComponents.CollidableComponent;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.SpriteComponent;
import entity.Entity;

public class BouncyBlock extends Entity {

	public BouncyBlock(int id, String imagePath, double xCoord, double yCoord, double width, double height, String label) {
		super(id);
		initialize(imagePath, xCoord, yCoord, width, height, label);
		//might want to make an object to transport things like imagepath, xcoord, y coord, width, height
	}

	
	private void initialize(String imagePath, double xCoord, double yCoord, double width, double height, String entLabel) {
		CollidableComponent collidable = new CollidableComponent(true);
		CheckCollisionComponent collisionCheck = new CheckCollisionComponent(false);
		SpriteComponent sprite = new SpriteComponent();
		
		sprite.setObject(imagePath);
		LocationComponent loc = new LocationComponent();
		loc.setXY(xCoord, yCoord);
		ImagePropertiesComponent imageDimensions = new ImagePropertiesComponent();
		imageDimensions.setHeight(height);
		imageDimensions.setWidth(width);
		if (entLabel!=null && !entLabel.equals("")) {
			LabelComponent label = new LabelComponent(entLabel);
			addComponent(label);
		}
		
		
			
		
		
	}
}
