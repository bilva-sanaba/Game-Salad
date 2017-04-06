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
	GridPane pane = new GridPane();
	TabPane myTab = new TabPane();
	Button b;
	ImageChooser chooser = new ImageChooser();
	
	public TabView(){
		b = util.buildButton(resources.getString("AddEntityButton"), e->chooser.chooseFile());
		//GridPane.setConstraints(myTab, 1, 1);
		Tab blockTab = util.buildTab(resources.getString("BlockTabLabel"), false);
		blockTab.setContent(blocksView);
		myTab.getTabs().add(blockTab);
	}
	
	public Region buildComponent(){
        //pane.getChildren().add(myTab);
		//GridPane.setConstraints(myTab, 0, 0);
		pane.getChildren().add(b);
		//GridPane.setConstraints(b, 1, 0);
		Region myRegion = pane;
		return myRegion;
	}
	
}
