package gameView.tools;

import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableFactory {

	private Double myWidth;
	private Double myHeight;
	
	/**
	 * Make a table given the correct width, height
	 * @param width
	 * @param height
	 */
	public TableFactory(Double width, Double height) {
		myWidth = width;
		myHeight = height;
	}
	
	public TableView<GameEntry> getTable(Collection<GameEntry> data, String... titles ) {
		return makeTable(data, titles);
	}
	
	private TableView<GameEntry> makeTable(Collection<GameEntry> newData, String... titles) {
		ObservableList<GameEntry> data =
	            FXCollections.observableArrayList(newData);
		TableView<GameEntry> gameBox = new TableView<GameEntry>();
		gameBox.setPrefWidth(myWidth);
		gameBox.setPrefHeight(myHeight);
		gameBox.setEditable(true);
		EntryValues values = new EntryValues();
		int count = 1;
		for (String each: titles) {
			TableColumn<GameEntry, String> column = makeColumn(each, values.getValue(count), myWidth/titles.length);
			gameBox.getColumns().add(column);
			count++;
		}
		gameBox.setItems(data);
		return gameBox; 
	}
	
	private TableColumn<GameEntry, String> makeColumn(String title, String value, double size) {
		TableColumn<GameEntry, String> column = new TableColumn<GameEntry, String>(title);
		column.setMinWidth(size);
		column.setCellValueFactory(
                new PropertyValueFactory<GameEntry, String>(value));
		return column;
	}
}
