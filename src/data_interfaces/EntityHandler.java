package data_interfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import components.IComponent;
import entity.*;
import voogasalad.util.reflection.*;

public class EntityHandler {
	
	private static final String IDSTRING = "identifier";
	private static final String COMPONENTSTRING = "myComponents";

	/**
	 * 
	 * @param d the document which can be received from XMLParser
	 * @return a collection of Entities from the XML file
	 */
	public Collection <Entity> getCollection(Element d) {
		Collection <Entity> ret = new ArrayList<Entity>();
		NodeList nm = d.getElementsByTagName("entity.Entity");
		
		for (int i = 0; i < nm.getLength(); i++) {
			Node n = nm.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				Element curr = (Element)n;
				ret.add(createEntity(curr));		
			}
		}
		return ret;
	}
	
	private Entity createEntity(Element curr) {
		NodeList children = curr.getChildNodes();
		int id = -1;
		List <IComponent> myCom = new ArrayList<IComponent>();
		
		for (int j = 0; j < children.getLength(); j++) {
			Node n2 = children.item(j);
			if (n2.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element)n2;
				if (e.getNodeName().equals(IDSTRING)) {
					id = Integer.parseInt(e.getTextContent());
				}
				else if (e.getNodeName().equals(COMPONENTSTRING)) {
					myCom = setComponents(e);
				}
			}
		}
		return initializeEntity(id, myCom);
	}
	
	private Entity initializeEntity(int id, List <IComponent> myCom) {
		Entity ret = new Entity(id);
		
		for (IComponent o: myCom) {
			ret.addComponent(o);
		}
		return ret;
	}
	
	private List <IComponent> setComponents(Element e) {
		List <IComponent> ret = new ArrayList<IComponent>();
		NodeList nl = e.getChildNodes();
		System.out.println("Component length: " + nl.getLength());
		
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element thisEl = (Element)nl.item(i);
				//System.out.println(i + " : " + thisEl.getTextContent() + " " + thisEl.getNodeName());
				IComponent ic = createIComponent(thisEl);
				ret.add(ic);
			}
			
		}
		
		return ret;
	}
	
	private IComponent createIComponent(Element e) {
		NodeList nl = e.getChildNodes();
		Object [] paramArray;
		List paramList = new ArrayList();
		
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element paramE = (Element)nl.item(i);
				//System.out.println(i + " : " + nl.item(i).getTextContent());
				if (isInteger(paramE.getTextContent())) {
					int temp = Integer.parseInt(paramE.getTextContent());
					paramList.add(temp);
				}
				else {
					paramList.add(paramE.getTextContent());
				}
			}
		}
		paramArray = paramList.toArray();
		
		return reflectionHandler(e, paramArray);
	}
	
	private IComponent reflectionHandler(Element e, Object [] paramArray) {
		Reflection reflector = new Reflection();
		String className = e.getNodeName();
		System.out.println(e.getNodeName());
		
		for (int i = 0; i < paramArray.length; i++) {
			System.out.println("this param: " + paramArray[i]);
		}
		System.out.println();
		return (IComponent)reflector.createInstance(className, paramArray);
	}
	
	private boolean isInteger(String input) {
	    try {
	        Integer.parseInt(input);
	        return true;
	    }
	    catch(NumberFormatException e) {
	        return false;
	    }
	}
}
