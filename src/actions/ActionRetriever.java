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

public class ActionRetriever {
	
	public static final String PACKAGE_NAME_WITH_ACTIONS = "actions";
	public static final String ACTIONS_PREFIX = "actions.";
	public static final String FILE_SUFFIX = ".class";

	public static final CollisionComponentType[] annotationTypes = {CollisionComponentType.Top, CollisionComponentType.Left, CollisionComponentType.Bottom,
			CollisionComponentType.Right};
	
	public static final Class<?>[] annotations = {TopAction.class, LeftAction.class, BottomAction.class, RightAction.class};
	
	private Map<CollisionComponentType, Class<?>> annotationMap;
	
	
	public ActionRetriever() {
		annotationMap = new HashMap<CollisionComponentType, Class<?>>();
		try {
			for(int i=0;i<annotationTypes.length;i++) {
				annotationMap.put(annotationTypes[i], annotations[i]);
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Need to catch the error in line 29 of ActionRetriever.java");
		}
		
	}
	
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
	
	
	private List<Class<?>> getActionClasses() {
		List<Class<?>> packageActions = new ArrayList<Class<?>>();
		URL packageUrl = Thread.currentThread().getContextClassLoader().getResource(PACKAGE_NAME_WITH_ACTIONS);
		File actionPackage = new File(packageUrl.getFile());
		for (File classFile : actionPackage.listFiles()) {
			Class actionClass = null;
			try {
				actionClass = Class.forName(ACTIONS_PREFIX + classFile.getName().split(FILE_SUFFIX)[0]);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Error needs to be handled on line 68 of ActionRetriever.java");
			}
			if (actionClass != null && !actionClass.equals(this.getClass())) {
				packageActions.add(actionClass);
			}
		}
		return packageActions;
	}
	
	
	

}
