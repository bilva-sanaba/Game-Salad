// This entire file is part of my masterpiece.
// Hamsa Pillai
package actions;

import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.eclipse.jetty.util.Loader;

import data_interfaces.LocalClassLoader;

import groovy.lang.GroovyClassLoader;

/**
 * This class is probably the most "advanced" feature that I implemented. This class uses groovystring to allow users to create their own IActions while they are in the Game Authoring Environment.
 * This is accomplished by them typing in the contents of the execute method for IAction and a name for the to-be-created IAction. This String will be passed in from the console to this class which will then attempt to
 * create and save the IAction with the given method implementation. This class is good design because it allows for the IAction framework to live up to its full potential
 * and literally allow the user to create any IAction effect they want WHILE running the Game Authoring Environment. At the same time, this is an interface. So, if someone else comes along and thinks that a different implementation
 * of turning Strings into executable objects, they can easily swap it out. At an extreme, this could be even used to turn the new IAction creation into something like the SLOGO parser/command framework.
 * @author Hamsa
 *
 */
public class UserActionFactory extends GroovyClassLoader implements IUserActionFactory {
	
	/**
	 * This String is used to specify which Script Engine to load.
	 */
	public static final String ENGINE_TYPE = "groovy";
	/**
	 * This String is used to make Groovy aware of IActions.
	 */
	public static final String IACTION_MAPPING = "IAction";
	/**
	 * This IActions is also used to make Groovy aware of IActions.
	 */
	public static final IAction ACTION_EXAMPLE = new BlockTopRegularCollision();
	/**
	 * This String is used to construct an IAction class.
	 */
	public static final String IMPORTS = "package actions; import components.entityComponents.*;"  
			+ " import entity.*; import gamedata.IRestrictedGameData; ";
	/**
	 * This String is also used to construct an IAction class.
	 */
	public static final String CLASS_AND_CONSTRUCTOR = "public class %s extends AbstractAction implements IAction{"
			 + " %s }";
	/**
	 * This String is also used to construct an IAction class.
	 */
	public static final String EXECUTE_METHOD = "public IRestrictedGameData executeAction(IEntity other, "
			+ "IEntity self, IEntityManager myEM, IRestrictedGameData currentGameData) { %s }";

	

	/**
	 * This map allows for retrieval of already-created groovy IActions.
	 */
	private Map<String, Class<?>> myClasses = new HashMap<>();
	/**
	 * This engine allows for the evaluation of strings into Groovy.
	 */
	private ScriptEngine engine;
	/**
	 * This loader allows for the creation of IAction classes.
	 */
	private LocalClassLoader classLoader;

	/**
	 * Constructor for UserActionFactory. Initializes Groovy engines.
	 */
	public UserActionFactory() {
		engine = new ScriptEngineManager().getEngineByName(ENGINE_TYPE);
		classLoader = new LocalClassLoader();
		engine.put(IACTION_MAPPING, ACTION_EXAMPLE);
	}
	
	 
	/**
	 * Creates and saves a new user-defined IAction class given the contents for the execute method and the name for the new IAction.
	 * @param executeMethodContents : the String representing the user's implementation of the execute method in IAction
	 * @param actionClassName : the String representing the name the user wants to assign to the IAction. Also used for retrievel of that class.
	 * @return the Class created if successful
	 * @throws InstantiationException if the creation was unsuccessful.
	 */
	public Class<?> createNewIAction(String executeMethodContents, String actionClassName) throws InstantiationException {
		try {
			StringBuilder buildClass = createClassString(executeMethodContents, actionClassName);
			Class<?> createdClass = classLoader.parseClass(buildClass.toString());
			classLoader.add(createdClass);
			myClasses.put(actionClassName, createdClass);
			return createdClass;
		} catch(Exception e) {
			throw new InstantiationException();
		}
		
	}


	/**
	 * Appends the user's execute implementation to the outline of an IAction class. This can then be used by the ScriptEngine to be evaluated as Java code.
	 * @param executeMethodContents
	 * @param actionClassName
	 * @return
	 */
	private StringBuilder createClassString(String executeMethodContents, String actionClassName) {
		StringBuilder buildClass = new StringBuilder();
		buildClass.append(IMPORTS);
		String completeExecuteMethod = String.format(EXECUTE_METHOD, executeMethodContents);
		buildClass.append(String.format(CLASS_AND_CONSTRUCTOR, actionClassName, completeExecuteMethod));
		return buildClass;
	}
	
	/**
	 * Creates a new instance of a defined user-created IAction class.
	 * @param actionClassName : the String representing the name of the class that has been created.
	 * @return an IAction object of that class if it exists
	 * @throws exception if that class has not been defined or if an object could not be made.
	 */
	public IAction createInstance(String actionClassName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return (IAction) (Loader.loadClass(actionClassName).newInstance());
	}
	
	
	
    
}
