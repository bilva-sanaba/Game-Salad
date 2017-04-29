package data_interfaces;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import entity.Entity;
import entity.IEntity;

public interface Writer {
	public void writeFile(String fileName, Map<Integer, List<IEntity>> gameData);
}
