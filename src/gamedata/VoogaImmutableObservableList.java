package gamedata;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;

public interface VoogaImmutableObservableList<E> {
	public int size();
	public E get(int index);
	public boolean contains(Object o);
	public boolean containsAll(Collection<?> c);
	public int indexOf(Object o);
	public boolean isEmpty();
	public int lastIndexOf(Object o);
	public ListIterator<E> listIterator();
	public ListIterator<E> listIterator(int index);
	public List<E> subList(int fromIndex, int toIndex);
	public Object[] toArray();
	public <T> T[] toArray(T[] a);
	public void addListener(InvalidationListener listener);
	public void removeListener(InvalidationListener listener);
	public void addListener(ListChangeListener<? super E> listener);
	public void removeListener(ListChangeListener<? super E> listener) ;
	public Iterator<E> iterator();
	
	
	
}
