package author_interfaces;

import java.io.File;
import javafx.scene.Scene;

public interface GUIbuilder {
	public Scene buildGUI();
	public Scene buildGUIFromFile(File f);
}
