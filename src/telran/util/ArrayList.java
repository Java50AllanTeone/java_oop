package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class ArrayList<T> implements List<T>, Iterable<T> {
	private static final int DEFAULT_CAPACITY = 16;
	private static final double DEFAULT_LOAD = 1;
	private T[] array;
	private int size = 0;
	private double loadFactor;
	
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity, double loadFactor) {
		this.array = (T[]) new Object[capacity];
		this.loadFactor = loadFactor;
	}
	
	public ArrayList() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD);
	}

	@SuppressWarnings("unchecked")
	public ArrayList(Collection<T> collection) {
		this(collection.size(), DEFAULT_LOAD);
		addAll(collection);
	}

	
	
	@Override
	public boolean add(T obj) {
		if (size >= array.length * loadFactor) {
			reallocate((array.length * 3) / 2 + 1);
		}
		array[size++] = obj;
		return true;
	}

	@Override
	public void add(int index, T object) {
		indexValidation(index, true);
		
		if (size >= array.length * loadFactor) {
			reallocate((array.length * 3) / 2 + 1);
		}
		
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = object;
		size++;
	}

	@Override
	public boolean remove(Object pattern) {
		var index = indexOf(pattern);
		
		try {
			remove(index);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public T remove(int index) {
		indexValidation(index, false);
		T res = array[index];
		size--;
		System.arraycopy(array, 0, array, 0, index);
		System.arraycopy(array, index + 1, array, index, size - index);
		array[size] = null;
		return res;
	}


	@Override
	public T[] toArray(T[] ar) {
		T[] res = ar.length < size ? Arrays.copyOf(ar, size) : ar;
		int index = 0;
		
		for (T obj : this) {
			res[index++] = obj;
		}
		
		if (res.length > size) {
			res[size] = null;
		}
		return res;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int oldSize = size;
		var it = iterator();
		
		while (it.hasNext()) {
			T next = it.next();
			if (predicate.test(next)) {
				it.remove();
			}
		}
		return oldSize > size;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public boolean addAll(Collection<T> collection) {
		int oldSize = size;
		
		//auto nullpointer validation
		for (T e : collection) {
			this.add(e);
		}		
		return oldSize < size;
	}

	@Override
	public boolean removeAll(Collection<T> collection) {
		int oldSize = size;
		
		// auto nullpointer validation
		for (T e : collection) {
			this.remove(e);
		}
		return oldSize > size;
	}

	@Override
	public Iterator<T> iterator() {		
		return new Iterator<T>() {
			int current = 0;
			boolean flNext = false;
			
			public boolean hasNext() {
				return current < size;
			}
			
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				flNext = true;
				return array[current++];
			}
			
			public void remove() {
				if (!flNext) {
					throw new IllegalStateException();
				}
				flNext = false;
				ArrayList.this.remove(--current);
			}
		};
	}

	@Override
	public T get(int index) {
		indexValidation(index, false);
		
		return array[index];
	}

	@Override
	public void set(int index, T object) {
		indexValidation(index, false);
		array[index] = object;
	}


	@Override
	public int indexOf(Object pattern) {
		for (int i = 0; i < size; i++) {
			if (pattern == null ? array[i] == null : array[i].equals(pattern)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int lastLeft = -1;
		
		for (int i = 0; i < size / 2 + 1; i++) {
			if (pattern == null ? array[size - i - 1] == null : array[size - i - 1].equals(pattern)) {
				return size - i - 1;
			}
			
			if (pattern == null ? array[i] == null : array[i].equals(pattern)) {
				lastLeft = i;
			}
		}
		return lastLeft;
	}

	@Override
	public int indexOf(Predicate<T> predicate) {		
		for (int i = 0; i < size; i++) {
			try {
				if (predicate.test(array[i])) {
					return i;
				}
			} catch (NullPointerException e) {}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		int lastLeft = -1;
		
		for (int i = 0; i < size / 2 + 1; i++) {
			try {
				if (predicate.test(array[size - i - 1])) {
					return size - i - 1;
				}
				
				if (predicate.test(array[i])) {
					lastLeft = i;
				}				
			} catch (NullPointerException e) {}
		}
		return lastLeft;
	}
	

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	public T[] clone() {
		//TODO
		return null;
	}
	
	public void removeRange(int from, int to) {
		//TODO
	}
	
	public void replaceAll(UnaryOperator<T> op) {
		//TODO
	}
	
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void ensureCapacity(int capacity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void trimToSize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addAll(int index, Collection<T> collection) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void sort(Comparator c) {
		// TODO Auto-generated method stub
		
	}
	
	
	private void reallocate(int capacity) {
		array = Arrays.copyOf(array, capacity);
	}
	private void indexValidation(int index, boolean sizeInclusive) {
		int bounder = sizeInclusive ? size : size - 1;
		
		if (index < 0 || index > bounder) {
			throw new IndexOutOfBoundsException(index);
		}
	}

	



}
