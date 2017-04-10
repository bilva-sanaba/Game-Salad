package data_interfaces;

import java.io.File;

public class GameSavingDataTool {
	private static final String PREFIX = "games" + File.separator;
	private static final String SUFFIX = ".xml";

	protected String getPrefix() {
		return PREFIX;
	}

	protected String getSuffix() {
		return SUFFIX;
	}
}
