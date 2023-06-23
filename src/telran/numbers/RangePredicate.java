package telran.numbers;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class RangePredicate implements Iterable<Integer> {
	int minInclusive;
	int maxExclusive;
	Predicate<Integer> predicate;
	
	public Predicate<Integer> getPredicate() {
		return predicate;
	}

	public void setPredicate(Predicate<Integer> predicate) {
		this.predicate = predicate;
	}

	
	public RangePredicate(int minInclusive, int maxExclusive) {
		if (minInclusive >= maxExclusive) {
			throw new IllegalArgumentException("min must be less than max");
		}
		
		this.minInclusive = minInclusive;
		this.maxExclusive = maxExclusive;
	}
	
	public int[] toArray() {
		//TODO
		return null;
	}




	private class RangePredicateIterator implements Iterator<Integer> {
		int current;
		Predicate<Integer> innerPredicate;
		
		RangePredicateIterator(Predicate<Integer> predicate) {
			this.innerPredicate = predicate;
			try {
				this.current = getNext(minInclusive);
			} catch (Exception e) {
				this.current = minInclusive;
			}
			
		}

		@Override
		public boolean hasNext() {
			try {
				getNext(this.current + 1);
				return true;
			} catch (NoSuchElementException e) {
				return false;
			}	
		}

		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			int res = current++;
			return res;
		}
		
		
		private int getNext(int min) {
			while (!this.innerPredicate.test(min)) {
				if (min > maxExclusive) {
					throw new NoSuchElementException();
				}
				min++;
			}
			return min;
		}
		
	}

	@Override
	public Iterator<Integer> iterator() {
		return new RangePredicateIterator(predicate);
	}
	
	
	
	

}
