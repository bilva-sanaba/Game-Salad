package actions;

import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import groovy.lang.GroovyClassLoader;

public class UserDefinedActionLoader extends GroovyClassLoader {
	
	private Map<String, Class<?>> myClasses = new HashMap<>();


	public UserDefinedActionLoader() {
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("groovy");
	}
	
	 
	

    /**
     * Add a new class definition to set of possible classes.
     */
    public void add (Class<?> c) {
        myClasses.put(c.getName(), c);
    }


    /**
     * @see GroovyClassLoader#loadClass(String)
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (myClasses.containsKey(name)) {
            return myClasses.get(name);
        }
        else {
            return super.loadClass(name);
        }
    }

}
