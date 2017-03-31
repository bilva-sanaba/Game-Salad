package data_interfaces;

import groovy.lang.GroovyClassLoader;

public interface LocalClassLoader extends GroovyClassLoader{
	public void add(Class<?> c);
	public Class<?> loadClass(String name) throws ClassNotFoundException;

}
