package controller;

import java.util.ArrayList;
import java.util.Collection;

import entity.restricted.RestrictedEntity;
import javafx.beans.InvalidationListener;

public class CollisionTracker extends Tracker {

	public CollisionTracker(String collisionMessage,
			Collection<RestrictedEntity> entities) {
		super(collisionMessage, entities);
		// TODO Auto-generated constructor stub
	}

}
