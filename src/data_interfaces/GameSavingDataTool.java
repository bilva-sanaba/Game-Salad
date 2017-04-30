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
	private static int SPLASHCONSTANT = 1;

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
	
	protected String getFileToString(String fileName) {
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
			// TODO add an error here
			System.out.println("Hi i happen");
			return null;
		} catch (IOException e) {
			System.out.println("hi i happen");
			return null;
		}
		return stringBuilder.toString();
	}
}
