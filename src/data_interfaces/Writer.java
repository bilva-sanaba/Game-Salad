package data_interfaces;

import java.util.Collection;

import entity.Entity;

public interface Writer {
	public void writeFile(String fileName, Collection<Entity> gameData);
}
