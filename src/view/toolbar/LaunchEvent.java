package view.toolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import data_interfaces.Communicator;
import data_interfaces.XMLWriter;
import entity.Entity;
import javafx.scene.control.TextInputDialog;
import view.UtilityFactory;
import view.ViewData;

public class LaunchEvent implements ToolBarButtonEvent {

	ViewData myData;

	public LaunchEvent(UtilityFactory utilF, ViewData data) {
		myData = data;
	}

	@Override
	public void event() {
		XMLWriter xw = new XMLWriter();
		String fileName;
		List <Entity> l = new ArrayList<Entity>();
		Map<Integer, Entity> m = myData.getDefinedEntityMap();
		for (Integer key : m.keySet()) {
			l.add(m.get(key));
		}
		if (myData.getGameName().equals("")) {
			TextInputDialog tid = new TextInputDialog(myData.getGameName());
			tid.setTitle("Saving File");
			tid.setHeaderText("Please choose a name for your game: ");
			Optional<String> result = tid.showAndWait();
			try {
				myData.setGameName(result.get());
				fileName = result.get();
				xw.writeFile(fileName, l);
			} catch (NoSuchElementException e) {
				return;
			}
		}
		Communicator c = new Communicator(myData.getGameName());
		// pass this value to the game engine
	}
}
