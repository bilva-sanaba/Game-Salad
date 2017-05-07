//THIS IS PART OF MY MASTERPIECE
package actions;

import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.eclipse.jetty.util.Loader;

import data_interfaces.LocalClassLoader;

import groovy.lang.GroovyClassLoader;


public class UserActionFactory extends GroovyClassLoader {
	
	public static final String ENGINE_TYPE = "groovy";
	public static final String IACTION_MAPPING = "IAction";
	public static final IAction ACTION_EXAMPLE = new BlockTopRegularCollision();
	public static final String IMPORTS = "package actions; import components.entityComponents.*;"  
			+ " import entity.*; import gamedata.IRestrictedGameData; ";
	public static final String CLASS_AND_CONSTRUCTOR = "public class %s extends AbstractAction implements IAction{"
			 + " %s }";
	public static final String EXECUTE_METHOD = "public IRestrictedGameData executeAction(IEntity other, "
			+ "IEntity self, IEntityManager myEM, IRestrictedGameData currentGameData) { %s }";
	
	

	
	private Map<String, Class<?>> myClasses = new HashMap<>();
	private ScriptEngine engine;
	private LocalClassLoader classLoader;


	public UserActionFactory() {
		engine = new ScriptEngineManager().getEngineByName(ENGINE_TYPE);
		classLoader = new LocalClassLoader();
		engine.put(IACTION_MAPPING, ACTION_EXAMPLE);
	}
	
	 
	
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



	private StringBuilder createClassString(String executeMethodContents, String actionClassName) {
		StringBuilder buildClass = new StringBuilder();
		buildClass.append(IMPORTS);
		String completeExecuteMethod = String.format(EXECUTE_METHOD, executeMethodContents);
		buildClass.append(String.format(CLASS_AND_CONSTRUCTOR, actionClassName, completeExecuteMethod));
		return buildClass;
	}
	
	public IAction createInstance(String actionClassName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return (IAction) (Loader.loadClass(actionClassName).newInstance());
	}
	
	
	
    
}
