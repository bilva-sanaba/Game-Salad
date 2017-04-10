package view;

import javafx.stage.Stage;

public class EntityConfigurationWindow {
	private UtilityFactory myUtilF;
	private ViewData myData;
	private Stage myStage;

	public EntityConfigurationWindow(UtilityFactory utilF, ViewData entityData) {
		myUtilF = utilF;
		myData = entityData;
		myStage = new Stage();
	}
}
