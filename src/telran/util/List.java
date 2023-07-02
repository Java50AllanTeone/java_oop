package telran.util;

import java.util.Comparator;
import java.util.function.Predicate;

public interface List<T> extends Collection<T> {
	
	void add(int index, T object);
	boolean addAll(int index, Collection<T> collection);
	T get(int index);
	void set(int index, T object);
	T remove(int index);
	int indexOf(T pattern);
	int lastIndexOf(T pattern);
	
	int indexOf(Predicate<T> predicate);
	int lastIndexOf(Predicate<T> predicate);
	void sort(Comparator c);

}
