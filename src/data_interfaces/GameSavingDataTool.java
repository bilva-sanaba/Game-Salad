package data_interfaces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GameSavingDataTool {
	private static final String PREFIX = "games" + File.separator;
	private static final String SUFFIX = ".xml";
	private static String LINESEPARATOR = System.getProperty("line.separator");
	public static int SPLASHCONSTANT = 1;
	private static final int SPLASHORDER = 2;
	private static final int LEVELORDER = 1;
	private static final int ENTITYORDER = 0;
	private static final int STORAGELEVEL = 1;

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
	
	protected int getSplashOrder() {
		return SPLASHORDER;
	}
	
	protected int getLevelOrder() {
		return LEVELORDER;
	}
	
	protected int getEntityOrder() {
		return ENTITYORDER;
	}
	
	protected int getStorageLevel() {
		return STORAGELEVEL;
	}
	
	/**
	 * Conversts the XML file to a string for parsing
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
}
