package controller;

import javafx.scene.image.Image;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import entity.restricted.IRestrictedEntity;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.beans.binding.DoubleBinding;

/**
 * 
 * @author Jacob
 *
 */
public class GameBuilder {

//	private HashMap<RestrictedEntity, ImageView> imageMap = new HashMap<RestrictedEntity, ImageView>();
//
//	public GameBuilder() {
//	}
//
//	public Scene setUpGame(Group root, RestrictedEntityManager manager,int height, int width) {
//		Collection<RestrictedEntity> entities = manager.getEntities();
//
//		for (RestrictedEntity entity : entities) {
//			Image image = new Image(getClass().getClassLoader().getResourceAsStream(entity.getImagePath()));
//			ImageView imageView = new ImageView(image);
//			imageView.setX(entity.getLocation().getX());
//			imageView.setY(entity.getLocation().getY());
//			root.getChildren().add(imageView);
//			imageMap.put(entity, imageView);
//		}
//
//		return new Scene(root, height, width);
//	}
//
//	public HashMap<RestrictedEntity, ImageView> getMap() {
//		return imageMap;
//	}
}
