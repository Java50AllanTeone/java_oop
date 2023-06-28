package telran.numbers;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class FilteredIterator<T> implements Iterator<T> {
	private Iterator<T> it;
	private Predicate<T> pred;
	
	private T current;
	private boolean hasNext = true;
	
	public FilteredIterator(Iterator<T> iterator, Predicate<T> filter) {
		this.it = iterator;
		this.pred = filter;	
		
		/*try {
			current = getNext();
		} catch (Exception e) {
			hasNext = false;
		}*/
		setNext();
	}

	@Override
	public boolean hasNext() {	
		return hasNext;
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		T res = current;
		
		/*try {
			current = getNext();
		} catch (Exception e) {
			hasNext = false;
		}*/
		setNext();
		return res;
	}
	
	/*private T getNext() {
		while (it.hasNext()) {
			var next = it.next();
			
			if (pred.test(next)) {
				return next;
			}
		}
		throw new NoSuchElementException();
	}*/
	
	
	private void setNext() {
		while (it.hasNext()) {
			var next = it.next();
			
			if (pred.test(next)) {
				this.current = next;
				return;
			}
		}
		this.hasNext = false;
	}

}
