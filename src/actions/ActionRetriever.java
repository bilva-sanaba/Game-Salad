// This entire file is part of my masterpiece.
// Hamsa Pillai

package actions;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import class_annotations.BottomAction;
import class_annotations.LeftAction;
import class_annotations.RightAction;
import class_annotations.TopAction;
import components.entityComponents.CollisionComponentType;

/**
 * This class retrieves all IActions in the actions package for a given CollisionComponentType/entity side. That is the only responsibility of this class. I think this class
 * constitutes good design because it makes extending the project much easier. Instead of having to worry about adding new IActions to resource files or figuring out where to place them, the user
 * only needs to add an annotation (@Top/Bottom/Left/RightAction) to the top of the IAction and make sure that the newly created IAction is in the actions package. This class will then automatically update the Game Authoring Environment
 * to show that class and allow it to reflected at runtime. Lastly, this also is an implementation of an interface so this implementation can be easily swapped out. Simply put, this makes the Game Authoring Environment's job much easier when it comes to dealing with IActions.
 * @author Hamsa
 *
 */
public class ActionRetriever implements IActionRetriever {
	
	/**
	 * This String is used to retrieve the package where IActions are located.
	 */
	public static final String PACKAGE_NAME_WITH_ACTIONS = "actions";
	/**
	 * This String is used to retrieve all files in the package with actions.
	 */
	public static final String ACTIONS_PREFIX = PACKAGE_NAME_WITH_ACTIONS + ".";
	/**
	 * This String is used to turn file names into Classes.
	 */
	public static final String FILE_SUFFIX = ".class";
	/**
	 * This int is used to choose the correct Class from an array;
	 */
	public static final int CLASS_INDEX = 0;
	/**
	 * This array of CollisionComponentTypes is used to map to possible annotations.
	 */
	public static final CollisionComponentType[] annotationTypes = {CollisionComponentType.Top, CollisionComponentType.Left, CollisionComponentType.Bottom,
			CollisionComponentType.Right};
	/**
	 * This array specifies the possible annotations, in the same order as the CollisionComponentType array.
	 */
	public static final Class<?>[] annotations = {TopAction.class, LeftAction.class, BottomAction.class, RightAction.class};
	
	private Map<CollisionComponentType, Class<?>> annotationMap;
	
	/**
	 * No-input constructor of ActionRetriever. Initializes annotation retrieval.
	 */
	public ActionRetriever() {
		annotationMap = new HashMap<CollisionComponentType, Class<?>>();
		for(int i=0;i<annotationTypes.length;i++) {
			annotationMap.put(annotationTypes[i], annotations[i]);
		}
			
		
		
	}
	
	/**
	 * Returns a list of classes that have the annotation specified by the CollisionComponentType input.
	 * @param typeOfAction : the enum that specifies which side of an entity you want to retrieve IActions for.
	 * @return list of IAction classes that are applicable for the given entity side.
	 */
	public List<Class<?>> getActionsWithAnnotation(CollisionComponentType typeOfAction) {
		List<Class<?>> actions = new ArrayList<Class<?>>();
		Class<? extends Annotation> desiredAnnotation = (Class<? extends Annotation>) annotationMap.get(typeOfAction);
		List<Class<?>> classesInPackage = getActionClasses();
		for (Class<?> actionClass : classesInPackage) {
			if (actionClass.isAnnotationPresent(desiredAnnotation)) {
				actions.add(actionClass);
			}
		}
		return actions;
	}
	
	/**
	 * Finds all IActions within their action package.
	 * @return list of IAction classes
	 */
	private List<Class<?>> getActionClasses() {
		List<Class<?>> packageActions = new ArrayList<Class<?>>();
		URL packageUrl = Thread.currentThread().getContextClassLoader().getResource(PACKAGE_NAME_WITH_ACTIONS);
		File actionPackage = new File(packageUrl.getFile());
		addToList(packageActions, actionPackage);
		return packageActions;
	}

	/**
	 * Turns a file into a class to be added to a list of classes.
	 * @param packageActions : the list to add the class to
	 * @param actionPackage : the package in which IActions can be found.
	 */
	private void addToList(List<Class<?>> packageActions, File actionPackage) {
		for (File classFile : actionPackage.listFiles()) {
			Class<?> actionClass = null;
			try {
				String className = ACTIONS_PREFIX + classFile.getName().split(FILE_SUFFIX)[CLASS_INDEX];
				actionClass = Class.forName(className);
			} catch (ClassNotFoundException e) {
				continue;
			}
			if (actionClass != null ) {
				packageActions.add(actionClass);
			}
		}
	}
	
	
	

}
