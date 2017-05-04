package view;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.SpriteComponent;
import data_interfaces.XMLDefinedParser;
import data_interfaces.XMLWriter;
import engines.infinite.InfiniteEnum;
import entity.Entity;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import view.window.EntityBuilderWindow;

public class TabView extends GUIComponent {
	private static final String PRESETFILE = "try1";
	private static final String SUFFIX = ".xml";

	private ObservableList<Entity> blocksList = FXCollections.observableArrayList();
	private ListView<Entity> blocksView = new ListView<Entity>();
	private VBox myBox = new VBox();
	private Button b;
	private Button savePresetButton;
	private Button loadPresetButton;
	private CheckBox cameraOption;
	private UtilityFactory util;
	private ViewData myData;
	private EntityBuilderWindow entityBuilder;
	private InfiniteEnum infinite;

	public TabView(UtilityFactory utilIn, ViewData data) {
		myBox.setSpacing(10);
		myBox.setPadding(new Insets(10));
		myData = data;
		util = utilIn;
		blocksView.setId("blocksview");
		blocksView.setItems(blocksList);
		blocksView.setCellFactory(e -> new ListCell<Entity>() {
			@Override
			protected void updateItem(Entity item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null || item.getComponent(ComponentType.Sprite) == null) {
					this.setGraphic(null);
				} else {
					SpriteComponent entitySprite = (SpriteComponent) item.getComponent(ComponentType.Sprite);
					ImageView spriteImage = new ImageView(entitySprite.getSprite());
					if (item.getComponent(ComponentType.ImageProperties) != null) {
						ImagePropertiesComponent imageProp = (ImagePropertiesComponent) item
								.getComponent(ComponentType.ImageProperties);
						spriteImage.setFitHeight(imageProp.getHeight());
						spriteImage.setFitWidth(imageProp.getWidth());
					} else {
						spriteImage.setFitHeight(40);
						spriteImage.setFitWidth(40);
					}
					this.setGraphic(spriteImage);
				}
			}
		});
		blocksView.setOrientation(Orientation.VERTICAL);
		blocksView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Entity>() {
			@Override
			public void changed(ObservableValue<? extends Entity> observable, Entity oldVal, Entity newVal) {
				myData.setUserSelectedEntity(newVal);
			}
		});
		b = util.buildButton("AddEntityButton", e -> {
			entityBuilder = new EntityBuilderWindow(util, blocksList, myData);
			entityBuilder.showEntityBuilder();
		});
		cameraOption = new CheckBox("Enable Camera");
		cameraOption.selectedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		        myData.getLevelEntity(1).setCamera(newValue);
		    }
		});
		savePresetButton = util.buildButton("SavePreset", e -> savePreset());
		loadPresetButton = util.buildButton("LoadPreset", e -> userLoadPreset());
		util.setPresets(blocksList);
		myData.getLevelEntityMap().get(1).setInfiniteEnum(InfiniteEnum.None);
	}

	public void setCamera(boolean b){
		cameraOption.selectedProperty().set(b);
	}
	
	public void clearSelected() {
		blocksView.getSelectionModel().clearSelection();
	}

	public void clearEntitiesOnTab() {
		blocksList.clear();
	}

	public void addEntity(Entity e) {
		blocksList.add(e);
	}

	public void addDefinedEntities() {
		for (int entityID : myData.getDefinedEntityMap().keySet()) {
			addEntity(myData.getDefinedEntityMap().get(entityID));
		}
	}

	public void addPresetEntities() {
		loadPreset(PRESETFILE);
	}

	@Override
	public Region buildComponent() {
		myBox.getChildren().addAll(blocksView,b,savePresetButton,loadPresetButton,cameraOption);
		HBox box = new HBox();
		final ToggleGroup group = util.buildRadioButtonGroup("InfiniteType", box);
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				String[] inf = (String[]) new_toggle.getUserData();
				infinite = InfiniteEnum.valueOf(inf[0]);
				myData.getLevelEntityMap().get(1).setInfiniteEnum(infinite);
			}
		});
		myBox.getChildren().add(box);
		return myBox;
	}

	public void selectEntity(Entity e) {
		blocksView.getSelectionModel().select(e);
	}

	private void savePreset() {
		TextInputDialog tid = new TextInputDialog(myData.getGameName());
		XMLWriter xw = new XMLWriter();
		tid.setTitle("Saving File");
		tid.setHeaderText("Please choose a name for your preset entities: ");
		Optional<String> result = tid.showAndWait();
		try {
			myData.setGameName(result.get());
			String fileName = result.get();

			Map<Integer, Entity> dem = myData.getDefinedEntityMap();

			List<Entity> l = new ArrayList<Entity>();

			for (Integer ie : dem.keySet()) {
				l.add(dem.get(ie));
			}
			xw.writeFile(fileName, l);
		} catch (NoSuchElementException e) {
			return;
		}
	}

	private void userLoadPreset() {
		Stage newStage = new Stage();
		FileChooser fc = new FileChooser();
		fc.setTitle("Choose the file to load: ");
		fc.setInitialDirectory(new File(System.getProperty("user.dir")));
		fc.getExtensionFilters().setAll(new ExtensionFilter("Text Files", "*" + SUFFIX));

		File dataFile = fc.showOpenDialog(newStage);
		if (dataFile != null) {
			String dataPath = dataFile.getAbsolutePath();
			String[] splitS = dataPath.split("[\\\\/]");
			String firstSplit = splitS[splitS.length - 1];
			String name = firstSplit.substring(0, firstSplit.length() - SUFFIX.length());
			loadPreset(name);
		}
	}

	private void loadPreset(String fileName) {
		XMLDefinedParser xdp = new XMLDefinedParser();
		List<Entity> l = xdp.getData(fileName);

		for (Entity e : l) {
			e.setID(myData.getDefinedEntityID());
			myData.defineEntity(e);
		}
	}

}