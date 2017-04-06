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
	private GridPane pane = new GridPane();
	private TabPane myTab = new TabPane();
	private Button b;
	private ImageChooser chooser = new ImageChooser();
	private UtilityFactory util;
	
	public TabView(UtilityFactory utilF){
		util = utilF;
		b = util.buildButton("AddEntityButton", e->chooser.chooseFile());
		//GridPane.setConstraints(myTab, 1, 1);
		Tab blockTab = util.buildTab("BlockTabLabel", false);
		blockTab.setContent(blocksView);
		myTab.getTabs().add(blockTab);
	}

	@Override
	public Region buildComponent(){
        pane.getChildren().add(myTab);
		GridPane.setConstraints(myTab, 0, 0);
		pane.getChildren().add(b);
		GridPane.setConstraints(b, 0, 1);
		Region myRegion = pane;
		GridPane.setConstraints(pane, 1, 1);
		return myRegion;
	}
	
}
