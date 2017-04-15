package data_interfaces;

import java.util.Collection;

import org.w3c.dom.Element;

import entity.Entity;

public interface Handler {
	public Collection<Entity> getCollection(Element d);
}
