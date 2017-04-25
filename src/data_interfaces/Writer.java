package data_interfaces;

import java.util.Collection;

import entity.Entity;
import entity.IEntity;

public interface Writer {
	public void writeFile(String fileName, Collection<IEntity> gameData);
}
