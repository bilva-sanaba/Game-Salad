package gamedata;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;


public class VoogaObservableList<E> implements ObservableList<E>, VoogaImmutableObservableList<E>, VoogaAddableObservableList<E> {
	private ObservableList<E> myList;
	public VoogaObservableList(List<E> list){
		myList= FXCollections.observableArrayList(list);
	}

	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		return myList.add(e);
	}

	@Override
	public void add(int index, E element) {
		myList.add(index,element);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return myList.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return myList.addAll(index,c);
	}

	@Override
	public void clear() {
		myList.clear();
		
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return myList.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return myList.containsAll(c);
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return myList.get(index);
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return myList.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return myList.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return myList.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return myList.lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return myList.listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return myList.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return myList.remove(o);
	}


	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return myList.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return myList.retainAll(c);
	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		return myList.set(index, element);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return myList.size();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return myList.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return myList.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return myList.toArray(a);
	}

	@Override
	public void addListener(InvalidationListener listener) {
		myList.addListener(listener);
		
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		myList.removeListener(listener);
		
	}

	@Override
	public boolean addAll(E... elements) {
		// TODO Auto-generated method stub
		return myList.addAll(elements);
	}

	@Override
	public void addListener(ListChangeListener<? super E> listener) {
		myList.addListener(listener);
		
	}

	@Override
	public void remove(int from, int to) {
		myList.remove(from, to);
		
	}

	@Override
	public boolean removeAll(E... elements) {
		// TODO Auto-generated method stub
		return myList.removeAll(elements);
	}

	@Override
	public void removeListener(ListChangeListener<? super E> listener) {
		myList.removeListener(listener);
		
	}

	@Override
	public boolean retainAll(E... elements) {
		// TODO Auto-generated method stub
		return myList.retainAll(elements);
	}

	@Override
	public boolean setAll(E... elements) {
		// TODO Auto-generated method stub
		return myList.setAll(elements);
	}

	@Override
	public boolean setAll(Collection<? extends E> col) {
		// TODO Auto-generated method stub
		return myList.setAll(col);
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return myList.remove(index);
	}

}


