package data_interfaces;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import components.entityComponents.LocationComponent;
import components.entityComponents.SpriteComponent;
import controller.VoogaAlert;
import data_interfaces.LocalClassLoader;
import entity.Entity;
import entity.IEntity;
import javafx.scene.control.Alert;

public class XMLWriter extends GameSavingDataTool implements Writer {
	
	private static final String ALERTMESSAGE = "File Saving Corrupted";

	private void createFile(String fileName, String data) {
		try {
			File f = new File(getPrefix() + fileName + getSuffix());
			BufferedWriter b = new BufferedWriter(new FileWriter(f));
			b.write(data.toString());
			b.close();
		} catch (Exception e) {
			VoogaAlert a = new VoogaAlert(ALERTMESSAGE);
			a.showAlert();
		}
	}

	public void writeFile(String fileName, Collection gameData) {
		ClassLoader loader = new LocalClassLoader();
		XStream serializer = new XStream(new DomDriver());
		String ret;
		serializer.setMode(XStream.NO_REFERENCES);

		serializer.setClassLoader(loader);

		ret = serializer.toXML(gameData);
		createFile(fileName, ret);
	}

	

}
