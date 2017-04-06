package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
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
		blocksView.setItems(blocksList);
		//blocksView.setOrientation(Orientation.VERTICAL);
		//blocksView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		util = utilF;
		Tab blockTab = util.buildTab("BlockTabLabel", false);
		blockTab.setContent(blocksView);
		b = util.buildButton("AddEntityButton", e->blocksList.add(chooser.chooseFile()));
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
