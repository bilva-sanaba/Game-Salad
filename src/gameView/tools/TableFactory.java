package gameView.tools;

import java.util.Collection;
import java.util.Iterator;

import gamedata.GameData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableFactory {

	private TableView<GameData> myTable;
	
	
	public TableFactory() {
	}
	
	public TableView<GameEntry> getTable(Collection<GameEntry> data, String... titles ) {
		return makeTable(data, titles);
	}
	
	private TableView<GameEntry> makeTable(Collection<GameEntry> newData, String... titles) {
		ObservableList<GameEntry> data =
	            FXCollections.observableArrayList();
		TableView<GameEntry> gameBox = new TableView<GameEntry>();
		gameBox.setPrefWidth(200);
		gameBox.setEditable(true);
		EntryValues values = new EntryValues(titles);
		for (String each: titles) {
			
		}
		TableColumn<GameEntry, String> first = new TableColumn<GameEntry, String>("Game");
        first.setMinWidth(100);
		first.setCellValueFactory(
                new PropertyValueFactory<GameEntry, String>("myFirstValue"));
		TableColumn<GameEntry, String> second = new TableColumn<GameEntry, String>("Points");
		second.setMinWidth(100);
		second.setCellValueFactory(
                new PropertyValueFactory<GameEntry, String>("mySecondValue"));

		gameBox.setItems(data);
		gameBox.getColumns().setAll(first, second);
		Iterator<String> dataIt = myData.getCurrentUser().getGames();
		while (dataIt.hasNext()) {
			String game = dataIt.next();
			System.out.println(game);
			data.add(new GameEntry(game, myData.getCurrentUser().getPointValue(game)));
		}
		gameBox.setPrefHeight(200);
		return gameBox; 
	}
	
	private TableColumn<GameEntry, String> makeColumn(String title, String value, int size) {
		TableColumn<GameEntry, String> column = new TableColumn<GameEntry, String>(title);
		column.setMinWidth(size);
		column.setCellValueFactory(
                new PropertyValueFactory<GameEntry, String>(value));
		return column;
	}
}
