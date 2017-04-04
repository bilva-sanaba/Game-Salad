package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class TabView extends GUIComponent{
	private ObservableList<ImageView> blocksList = FXCollections.observableArrayList();
	private ListView<ImageView> blocksView = new ListView<ImageView>();
	TabPane myTab = new TabPane();
	
	public TabView(){
		GridPane.setConstraints(myTab, 1, 1);
		Tab blockTab = new Tab();
		blockTab.setText("Blocks");
		blockTab.setContent(blocksView);
		blockTab.setClosable(false);
		myTab.getTabs().add(blockTab);
	}
	
	public Region buildComponent(){
		Region myRegion = myTab;
		return myRegion;
	}
	
}
