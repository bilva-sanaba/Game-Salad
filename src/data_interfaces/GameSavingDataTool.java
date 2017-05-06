// This entire file is part of my masterpiece.
// Josh Kopen

package data_interfaces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import entity.Entity;
import entity.LevelEntity;
import entity.SplashData;

/**
 * I believe this is good design as I finally put the parent class to use, making it abstract and making
 * it justified as a parent class for its children by providing to them valuable information. This class
 * uses a map mapped from Class to Lambda function so that the other parts of the project will always
 * know what to do with a specific class type. This is improved because of its new centralization which
 * makes sure that if the way the data needed to be interpreted changed, it would change everywhere in the
 * project.
 * @author joshuakopen
 *
 */

public abstract class GameSavingDataTool {
	private static final String PREFIX = "games" + File.separator;
	private static final String SUFFIX = ".xml";
	private static final String LINESEPARATOR = System.getProperty("line.separator");
	private static final int SPLASHCONSTANT = 1;
	private static final int STORAGELEVEL = 1;
	
	private Map<Class<? extends Entity>, Function> functionMap = new HashMap<Class<? extends Entity>, Function>();
	
	public GameSavingDataTool () {
		functionMap.put(SplashData.class, e -> getSplashData((Map<Class<? extends Entity>, Map>) e));
		functionMap.put(Entity.class, e -> getPlacedEntityMap((Map<Class<? extends Entity>, Map>) e));
		functionMap.put(LevelEntity.class, e -> getLevelEntityMap((Map<Class<? extends Entity>, Map>) e));
	}
	
	protected String getPrefix() {
		return PREFIX;
	}

	protected String getSuffix() {
		return SUFFIX;
	}
	
	protected String getLineSeparator() {
		return LINESEPARATOR;
	}
	
	protected int getSplashConstant() {
		return SPLASHCONSTANT;
	}
	
	protected int getStorageLevel() {
		return STORAGELEVEL;
	}
	
	protected Map<Class<? extends Entity>, Function> getFunctionMap() {
		return functionMap;
	}
	
	/**
	 * Converts the XML file to a string for parsing
	 * @param fileName the name of the file without the prefix or suffix
	 * @return the String of the XML file
	 * @throws Exception
	 */
	protected String getFileToString(String fileName) throws Exception {
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		try {
			File f = new File(getPrefix() + fileName + getSuffix());
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(getLineSeparator());
			}
			br.close();		
		} catch (FileNotFoundException e) {
			throw new Exception("FileNotFound");
		} catch (IOException e) {
			throw new Exception("IO");
		}
		return stringBuilder.toString();
	}
	
	protected SplashData getSplashData(Map<Class<? extends Entity>, Map> passed) {
		Map <Integer, SplashData> splashMap = passed.get(SplashData.class);
		return splashMap.get(getSplashConstant());
	}
	
	protected Map<Integer, Map<Integer, Entity>> getPlacedEntityMap(Map<Class<? extends Entity>, Map> passed) {
		return passed.get(Entity.class);
	}
	
	protected Map<Integer, LevelEntity> getLevelEntityMap(Map<Class<? extends Entity>, Map> passed) {
		return passed.get(LevelEntity.class);
	}
}
