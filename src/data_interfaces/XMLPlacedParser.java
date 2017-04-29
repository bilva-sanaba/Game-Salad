package data_interfaces;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import entity.Entity;

public class XMLPlacedParser implements Parser {

	private List<Map<Integer,Entity>> loadFile(String fileName) {
		XStream xs = new XStream(new DomDriver());
		return (List<Map<Integer,Entity>>) xs.fromXML(new File(fileName));
	}

	@Override
	public List getData(String fileName) {
		return loadFile(fileName);
	}

}
