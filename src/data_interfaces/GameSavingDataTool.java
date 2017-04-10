package data_interfaces;

public class GameSavingDataTool {
	private static final String PREFIX = "games/";
	private static final String SUFFIX = ".xml";

	protected String getPrefix() {
		return PREFIX;
	}

	protected String getSuffix() {
		return SUFFIX;
	}
}
