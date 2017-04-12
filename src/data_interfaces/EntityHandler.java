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
	private static final String ROWSTRING = "rows";
	private static final String COLSTRING = "cols";
	private static final String BACKGROUNDSTRING = "backgroundFilePath";
	private static final String DNSTRING = "displayName";
	private static final String INSTSTRING = "instructions";

	/**
	 * 
	 * @param d
	 *            the document which can be received from XMLParser
	 * @return a collection of Entities from the XML file
	 */
	public Collection<Entity> getCollection(Element d) {
		Collection<Entity> ret = new ArrayList<Entity>();
		NodeList nm = d.getElementsByTagName("entity.SplashEntity");
		ret.add(getSplashEntity(nm));
		nm = d.getElementsByTagName("entity.LevelEntity");
		ret.add(getLevelEntity(nm));
		nm = d.getElementsByTagName("entity.Entity");
		doEntirely(ret, nm);
		return ret;
	}
	
	private SplashEntity getSplashEntity(NodeList nm) {
		Node n = nm.item(0);
		Element curr = (Element) n;
		return createSplashEntity(curr);
	}
	private SplashEntity createSplashEntity(Element curr) {
		NodeList children = curr.getChildNodes();
		int id = -1;
		String displayName = "";
		String instructions = "";
		String bfp = "";
		List <IComponent> myCom = new ArrayList<IComponent>();
		
		for (int i = 0; i < children.getLength(); i++) {
			Node n2 = children.item(i);
			if (n2.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) n2;
				if (e.getNodeName().equals(IDSTRING)) {
					id = Integer.parseInt(e.getTextContent());
				} else if (e.getNodeName().equals(COMPONENTSTRING)) {
					myCom = setComponents(e);
				} else if (e.getNodeName().equals(DNSTRING)) {
					displayName = e.getTextContent();
				} else if (e.getNodeName().equals(INSTSTRING)) {
					instructions = e.getTextContent();
				} else if (e.getNodeName().equals(BACKGROUNDSTRING)) {
					bfp = e.getTextContent();
				}
			}
		}
		
		return initializeSplashEntity(id, displayName, instructions, bfp, myCom);
	}
	
	private SplashEntity initializeSplashEntity(int id, String displayName, String instructions, String bfp, List<IComponent> myCom) {
		SplashEntity ret = new SplashEntity(id, displayName, instructions, bfp);
		return (SplashEntity)attachComponents(ret,myCom);
	}
	
	private LevelEntity getLevelEntity(NodeList nm) {
		Node n = nm.item(0);
		Element curr = (Element) n;
		return createLevelEntity(curr);
	}
	
	private LevelEntity createLevelEntity(Element curr) {
		NodeList children = curr.getChildNodes();
		int id = -1;
		int rows = -1;
		int cols = -1;
		String bfp = "";
		List <IComponent> myCom = new ArrayList<IComponent>();
		
		for (int i = 0; i < children.getLength(); i++) {
			Node n2 = children.item(i);
			if (n2.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) n2;
				if (e.getNodeName().equals(IDSTRING)) {
					id = Integer.parseInt(e.getTextContent());
				} else if (e.getNodeName().equals(COMPONENTSTRING)) {
					myCom = setComponents(e);
				} else if (e.getNodeName().equals(ROWSTRING)) {
					rows = Integer.parseInt(e.getTextContent());
				} else if (e.getNodeName().equals(COLSTRING)) {
					cols = Integer.parseInt(e.getTextContent());
				} else if (e.getNodeName().equals(BACKGROUNDSTRING)) {
					bfp = e.getTextContent();
				}
			}
		}
		
		return initializeLevelEntity(id, rows, cols, bfp, myCom);
	}
	
	private void doEntirely(Collection <Entity> ret, NodeList nm) {
		for (int i = 0; i < nm.getLength(); i++) {
			System.out.println(nm.getLength() + "<-THIS HAPPENS HERE");
			Node n = nm.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				Element curr = (Element) n;
				ret.add(createEntity(curr));
			}
		}
	}

	private Entity createEntity(Element curr) {
		NodeList children = curr.getChildNodes();
		int id = -1;
		List<IComponent> myCom = new ArrayList<IComponent>();

		for (int j = 0; j < children.getLength(); j++) {
			Node n2 = children.item(j);
			if (n2.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) n2;
				if (e.getNodeName().equals(IDSTRING)) {
					id = Integer.parseInt(e.getTextContent());
				} else if (e.getNodeName().equals(COMPONENTSTRING)) {
					myCom = setComponents(e);
				}
			}
		}
		return initializeEntity(id, myCom);
	}

	private Entity initializeEntity(int id, List<IComponent> myCom) {
		Entity ret = new Entity(id);

		return attachComponents(ret, myCom);
	}
	
	private LevelEntity initializeLevelEntity(int id, int rows, int cols, String bfp, List <IComponent> myCom) {
		LevelEntity ret = new LevelEntity(id, rows, cols, bfp);
		
		return (LevelEntity)attachComponents(ret, myCom);
	}
	
	private Entity attachComponents(Entity ret, List <IComponent> myCom) {
		for (IComponent o : myCom) {
			ret.addComponent(o);
		}
		return ret;
	}

	private List<IComponent> setComponents(Element e) {
		List<IComponent> ret = new ArrayList<IComponent>();
		NodeList nl = e.getChildNodes();

		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element thisEl = (Element) nl.item(i);
				// System.out.println(i + " : " + thisEl.getTextContent() + " "
				// + thisEl.getNodeName());
				IComponent ic = createIComponent(thisEl);
				ret.add(ic);
			}

		}

		return ret;
	}

	private IComponent createIComponent(Element e) {
		NodeList nl = e.getChildNodes();
		Object[] paramArray;
		List paramList = new ArrayList();

		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element paramE = (Element) nl.item(i);
				// System.out.println(i + " : " + nl.item(i).getTextContent());
				if (isInteger(paramE.getTextContent())) {
					int itemp = Integer.parseInt(paramE.getTextContent());
					paramList.add(itemp);
				} 
				else if (isDouble(paramE.getTextContent())){
					double dtemp = Double.parseDouble(paramE.getTextContent());
					paramList.add(dtemp);
				}
				else {
					paramList.add(paramE.getTextContent());
				}
			}
		}
		paramArray = paramList.toArray();

		return reflectionHandler(e, paramArray);
	}

	private IComponent reflectionHandler(Element e, Object[] paramArray) {
		Reflection reflector = new Reflection();
		String className = e.getNodeName();
		System.out.println(e.getNodeName());

		for (int i = 0; i < paramArray.length; i++) {
			//System.out.println("this param: " + paramArray[i]);
			//System.out.println("this type: " + paramArray[i].getClass());
		}
		//System.out.println();
		return (IComponent) reflector.createInstance(className, paramArray);
	}

	private boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	private boolean isDouble(String input) {
		try {
			Double.parseDouble(input);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}
}
