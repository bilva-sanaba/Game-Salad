package data_interfaces;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import entity.Entity;

public class XMLPlacedParser extends GameSavingDataTool implements Parser {

	private List<Map> loadFile(String fileName) {
		XStream xs = new XStream(new DomDriver());
		System.out.println(getFileToString(fileName));
		return (List<Map>) xs.fromXML(getFileToString(fileName));
	}

	@Override
	public List getData(String fileName) {
		return loadFile(fileName);
	}

}
