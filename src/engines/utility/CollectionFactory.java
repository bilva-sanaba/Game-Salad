// This entire file is part of my masterpiece.
// Bilva Sanaba
// This simple class follows the factory pattern and highlights good design by removing duplicate code from various classes
// which may want a shallow copy of a collection. Additionally, it uses generics in order to further increase the factory flexibility.
package engines.utility;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionFactory<E> {
	public Collection<E> copyCollection(Collection<E> myElements){
		Collection<E> safeCopy = new ArrayList<E>();
		safeCopy.addAll(myElements);
		return safeCopy;
	}
}