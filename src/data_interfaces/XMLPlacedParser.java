package data_interfaces;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import controller.VoogaAlert;
import entity.Entity;

public class XMLPlacedParser extends GameSavingDataTool implements Parser {
	
	private final static String ALERTMESSAGE = "This file is corrupted!";

	private List<Map> loadFile(String fileName) {
		XStream xs = new XStream(new DomDriver());
		try {
			return (List<Map>) xs.fromXML(getFileToString(fileName));
		} catch (Exception e) {
			VoogaAlert a = new VoogaAlert(ALERTMESSAGE);
			a.showAlert();
			return null;
		}
	}

	@Override
	public List getData(String fileName) {
		return loadFile(fileName);
	}

}
