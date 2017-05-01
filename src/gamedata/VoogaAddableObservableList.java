package gamedata;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public interface VoogaAddableObservableList<E> {

	public boolean add(E e);

	public void add(int index, E element) ;

	public boolean addAll(Collection<? extends E> c) ;

	public boolean addAll(int index, Collection<? extends E> c);

	public void clear() ;

	public boolean contains(Object o);

	public boolean containsAll(Collection<?> c) ;

	public E get(int index);

	public int indexOf(Object o);

	public boolean isEmpty();

	public Iterator<E> iterator();

	public int lastIndexOf(Object o);

	public ListIterator<E> listIterator();

	public ListIterator<E> listIterator(int index);

	public boolean remove(Object o) ;

	public E remove(int index) ;

	public boolean removeAll(Collection<?> c);
	
	public boolean retainAll(Collection<?> c) ;
	
	public E set(int index, E element) ;
	
	public int size();
	
	public List<E> subList(int fromIndex, int toIndex);
	
	public Object[] toArray() ;
	
	public <T> T[] toArray(T[] a) ;

	public boolean addAll(E... elements);

	public void remove(int from, int to) ;

	public boolean removeAll(E... elements);

	public boolean retainAll(E... elements);

	public boolean setAll(E... elements) ;

	public boolean setAll(Collection<? extends E> col);

}




