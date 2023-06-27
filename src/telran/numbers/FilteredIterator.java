package telran.numbers;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class FilteredIterator<T> implements Iterator<T> {
	private Iterator<T> it;
	private Predicate<T> pred;
	private T current;
	
	public FilteredIterator(Iterator<T> iterator, Predicate<T> filter) {
		this.it = iterator;
		this.pred = filter;	
		current = getNext();
	}

	@Override
	public boolean hasNext() {	
		return current != null;
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		T res = current;
		current = getNext();
		return res;
	}
	
	private T getNext() {
		T next = null;
		
		while (it.hasNext()) {
			next = it.next();
			
			if (pred.test(next)) {
				return next;
			}
		}
		return null;
	}

}
