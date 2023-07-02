package telran.util;

import java.util.function.Predicate;

public interface Collection<T> extends Iterable<T> {
	boolean add(T obj);
	boolean remove(Object pattern);
	T[] toArray(T[] array);
	Object[] toArray();
	boolean removeIf(Predicate<T> predicate);
	int size();
	boolean addAll(Collection<T> collection);
	
	boolean removeAll(Collection<T> collection);
	boolean isEmpty();
	void clear();
	boolean contains(Object o);
	void ensureCapacity(int capacity);
	boolean retainAll(Collection<T> c);
	public void trimToSize();
	
}
